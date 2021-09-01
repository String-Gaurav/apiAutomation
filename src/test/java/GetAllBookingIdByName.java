import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utility.AllureLogger;
import utility.BaseTest;
import utility.ExcelLib;
import utility.ExtentReportListener;

import static io.restassured.RestAssured.given;

@Listeners(ExtentReportListener.class)
public class GetAllBookingIdByName extends BaseTest {


    @Test(description = "To fetch the details of the booking IDs based on First and Last Name")
    public void getAllBookingByName() {
        String firstName = "Sally";
        String lastName = "Brown";
        test.log(LogStatus.INFO, "Starting the test to get booking details");
        /*
         * Send a GET request to /booking?firstname={firstName}&lastname={lastName}
         * and check that the response has HTTP status code 200
         **/

        //Sending the GET request for a specific booking id and receiving the response

        test.log(LogStatus.INFO, "Getting the response by giving the request as First and Last Name");
        Response getResponse = given().
                spec(requestSpec).
                queryParam("firstname", firstName).
//                queryParam("lastname", lastName).
                when().
                get("/booking");
        //Verify the response code

        AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
        getResponse.then().spec(responseSpec);
        //To log the response to report

        logResponseAsString(getResponse);
        //When test case get pass
        test.log(LogStatus.PASS, "Test got Passed");


    }
}
