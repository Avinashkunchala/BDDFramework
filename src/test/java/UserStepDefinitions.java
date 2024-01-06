import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserStepDefinitions {

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

    @When("^the user requests details for user with ID \"([^\"]*)\"$")
    public void the_user_requests_details_for_user_with_id(String userId) {
        response = request.when().get("/users/" + userId);
    }

    @Then("^the details for the user should be successfully retrieved$")
    public void the_details_for_the_user_should_be_successfully_retrieved() {
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("^the response should include user details like name \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void the_response_should_include_user_details_like_name_and_email(String name, String email) {
        Assert.assertEquals(response.jsonPath().get("name"), name);
        Assert.assertEquals(response.jsonPath().get("email"), email);
    }
}

