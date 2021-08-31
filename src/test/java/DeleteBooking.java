import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utility.AllureLogger;
import utility.BaseTest;
import utility.ExtentReportListener;

import static io.restassured.RestAssured.given;

@Listeners(ExtentReportListener.class)
public class DeleteBooking extends BaseTest {

    @Test(description = "To delete the details of the booking IDs")
    public void deleteBooking() {
        test.log(LogStatus.INFO, "Starting the test to delete booking details");
        /*
         * Send a DELETE request to /booking/{id}
         * and check that the response has HTTP status code 200
         ******************************************************/

        //To get the auth token

        String newAuthToken = AuthToken.post_CreateAuth();
        AllureLogger.logToAllure("Auth token is : " + newAuthToken);
        test.log(LogStatus.INFO, "Auth token is : " + newAuthToken);
        String cookieValue = "token=" + newAuthToken;
        //Created a new booking, this will be subsequently deleted

        CreateBooking createBooking = new CreateBooking();
        createBooking.createNewBooking("Sanda", "Jon", "114", "false", "2018-01-03", "2018-01-05", "Dinner", null);
        String IDtoUpdate = createBooking.newID;
        test.log(LogStatus.INFO, "New Booking ID created is : " + IDtoUpdate);
        test.log(LogStatus.INFO, "Booking ID to be deleted is : " + IDtoUpdate);
        //Sending the GET request for a specific booking id and receiving the response

        test.log(LogStatus.INFO, "Sending the GET request for a specific booking id and receiving the response");
        Response response = given().
                spec(requestSpec).
                header("Content-Type", "application/json").
                header("Cookie", cookieValue).
                pathParam("id", IDtoUpdate).
                when().
                delete("/booking/{id}");
        //Verify the response code

        test.log(LogStatus.PASS, "Asserting the response if the status code returned is 201 as this is a Delete request");
        response.then().assertThat().statusCode(201);
        //To log the response to report

        logResponseAsString(response);

    }
}
