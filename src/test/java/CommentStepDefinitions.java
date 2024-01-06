import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommentStepDefinitions {

    private Response response;
    private RequestSpecification request;
    private static String baseURI;

    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/test/resources/config.properties"));
            baseURI = props.getProperty("baseURI");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file");
        }
    }

    @Given("^the user is authenticated$")
    public void the_user_is_authenticated() {
        RestAssured.baseURI = baseURI;
        request = given().auth().preemptive().basic("username", "password");
    }

    @Given("^a post with ID \"([^\"]*)\" exists$")
    public void a_post_with_id_exists(String postId) {
        // Optionally, verify if the post exists, or prepare data for the test
    }

    @When("^the user submits a comment with postId \"([^\"]*)\", name \"([^\"]*)\", email \"([^\"]*)\", and body \"([^\"]*)\"$")
    public void the_user_submits_a_comment(String postId, String name, String email, String commentBody) {
        response = request.when()
                .body(String.format("{\"postId\": %s, \"name\": \"%s\", \"email\": \"%s\", \"body\": \"%s\"}",
                        postId, name, email, commentBody))
                .post("/comments");
    }

    @Then("^the comment should be successfully added$")
    public void the_comment_should_be_successfully_added() {
        Assert.assertEquals(response.getStatusCode(), 201); // Assuming 201 is the status code for successful creation
    }

    @Then("^the response should include the comment details with name \"([^\"]*)\", email \"([^\"]*)\", and body \"([^\"]*)\"$")
    public void the_response_should_include_the_comment_details(String name, String email, String commentBody) {
        Assert.assertEquals(response.jsonPath().get("name"), name);
        Assert.assertEquals(response.jsonPath().get("email"), email);
        Assert.assertEquals(response.jsonPath().get("body"), commentBody);
    }

    @When("^the user retrieves comments for the post with ID \"([^\"]*)\"$")
    public void the_user_retrieves_comments_for_the_post(String postId) {
        response = request.when().get("/comments?postId=" + postId);
    }

    @Then("^the comments for the post should be successfully retrieved$")
    public void the_comments_for_the_post_should_be_successfully_retrieved() {
        Assert.assertEquals(response.getStatusCode(), 200);
        // Additional assertions can be added to verify the contents of the comments
    }
}

