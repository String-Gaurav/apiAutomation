import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utility.AllureLogger;
import utility.BaseTest;
import utility.ExtentReportListener;
import static io.restassured.RestAssured.given;

@Listeners(ExtentReportListener.class)
public class PartialUpdateBooking extends BaseTest {

    @Test(description = "To update the details of the booking IDs")
    public void PartialUpdateBooking() {
        test.log(LogStatus.INFO, "Starting the test to update details");
        AllureLogger.logToAllure("Starting the test to update details");
        /*
         * Send a PUT request to /booking/{id}
         * and check that the response has HTTP status code 200
         ******************************************************/
        //Sending the PUT request for a specific booking id and receiving the response after updating the detals

        test.log(LogStatus.INFO, "PUT update booking detail");
        //To get the auth token
        String newAuthToken = AuthToken.post_CreateAuth();
        test.log(LogStatus.INFO, "Auth token is : " + newAuthToken);

        //Created a new booking
        CreateBooking createBooking = new CreateBooking();
        createBooking.createNewBooking("Sanda", "Varys", "114", "false", "2018-01-03", "2018-01-05", "Dinner", null);
        String IDtoUpdate = CreateBooking.newID;
        test.log(LogStatus.INFO, "New Booking ID created is : " + IDtoUpdate);

        //Setting the value to be sent in the patch request
        String setValue = "{\"firstname\":\"Gaurav\"}";
        String cookieValue = "token=" + newAuthToken;
        System.out.println("cookieValue is :" + cookieValue);
        //Sending the PATCH request

        test.log(LogStatus.INFO, "Sending the PATCH request to partially update the booking detail of booking id : " + IDtoUpdate);
        Response response = given().
                spec(requestSpec).
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                header("Cookie", cookieValue).
                pathParam("id", IDtoUpdate).
                body(setValue).log().body().
                when().
                patch("/booking/{id}");

        //Verify the response code
        AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
        test.log(LogStatus.PASS, "Asserting the response if the status code returned is 200");
        response.then().spec(responseSpec);

        //To log the response to report
        logResponseAsString(response);

    }
}
