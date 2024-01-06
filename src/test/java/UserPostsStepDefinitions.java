import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserPostsStepDefinitions {

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
        // Setup RestAssured request with authentication
        RestAssured.baseURI = baseURI;
        request = given().auth().preemptive().basic("username", "password");
    }

    @When("^the user submits a new post with title \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void the_user_submits_a_new_post_with_title_and_body(String title, String body) {
        response = request.when()
                .body("{\"title\":\"" + title + "\", \"body\":\"" + body + "\"}")
                .post("/posts");
    }

    @Then("^the post should be successfully created$")
    public void the_post_should_be_successfully_created() {
        Assert.assertEquals(response.getStatusCode(), 201); // Assuming 201 is the status code for successful creation
    }

    @Then("^the response should include the post details with title \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void the_response_should_include_the_post_details(String title, String body) {
        Assert.assertEquals(response.jsonPath().get("title"), title);
        Assert.assertEquals(response.jsonPath().get("body"), body);
    }
}


