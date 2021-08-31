
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Listeners;
import static io.restassured.RestAssured.given; //import this
import io.restassured.response.Response;
import utility.*;
import org.json.simple.JSONObject;

@Listeners(ExtentReportListener.class)
public class AuthToken extends BaseTest {


    public static String post_CreateAuth() {
        test.log(LogStatus.INFO, "Starting the test for POST method for create authentication");
        AllureLogger.logToAllure("Starting the test for POST method for create authentication");
        /*
         Send a POST request to /auth
         and check that the response has HTTP status code 200
         */

        JSONObject jsonObject = returDefaultPayLoadObject(FrameworkConstants.POSTRequest_AUTH_DEFAULT_REQUEST);
        String username = readConfigurationFile("username");
        String password = readConfigurationFile("password");
        jsonObject.put("password", password);
        jsonObject.put("username", username);
        test.log(LogStatus.INFO, "Username from config file is : \n" + username);
        test.log(LogStatus.INFO, "Password from config file is : \n" + password);


        Response response = given().
                spec(requestSpec).
                contentType("application/json").
                body(jsonObject.toJSONString()).
                when().
                post("/auth");

        AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
        test.log(LogStatus.PASS, "Asserting the response if the status code returned is 200");
        response.then().spec(responseSpec);


        String token = response.then().extract().path("token");
        return token;
    }
}
