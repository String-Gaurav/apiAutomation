
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given; //import this
import io.restassured.response.Response;
import pojoClasses.BookingDetails;
import utility.AllureLogger;
import utility.BaseTest;
import utility.ExcelLib;
import utility.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class GetAllBookingIds extends BaseTest {
    @Test(description = "To fetch the details of the booking IDs")

    public void getAllBookingIDs() {
        test.log(LogStatus.INFO, "Starting the test to get booking details");
        /*
         * Send a GET request to /booking
         * and check that the response has HTTP status code 200
         */

        //Sending the GET request for a specific booking id and receiving the response
        test.log(LogStatus.INFO, "Going to the fetch all the booking id's");
        Response response = given().
                spec(requestSpec).
                when().
                get("/booking");

        //Verify the response code
        AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
        response.then().spec(responseSpec);

        //To log the response to report
        logResponseAsString(response);


        //When test case get pass
        test.log(LogStatus.PASS, "Test got Passed");



    }
}
