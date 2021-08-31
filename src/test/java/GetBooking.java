
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
public class GetBooking extends BaseTest {

    @DataProvider(name = "DataFromExcel")
    public Object[][] data() throws Exception {
        ExcelLib xl = new ExcelLib("Booking", this.getClass().getSimpleName());
        return xl.getTestdata();
    }

    @Test(dataProvider = "DataFromExcel", description = "To retrieve the details of the booking IDs")
    public void getBookingIDs(String ID,
                              String firstName,
                              String lastName,
                              String totalPrice,
                              String depositPaid,
                              String checkIn,
                              String checkOut,
                              String additionalneeds, String dontUseThis
    ) {
        test.log(LogStatus.INFO, "Starting the test to get booking details");
        /*******************************************************
         * Send a GET request to /booking/{id}
         * and check that the response has HTTP status code 200
         ******************************************************/

        //Sending the GET request for a specific booking id and receiving the response
        test.log(LogStatus.INFO, "Getting the response for the Booking IDs from test data excel");
        Response response = given().
                spec(requestSpec).
                pathParam("id", ID).
                when().
                get("/booking/{id}");

        //Verify the response code
        AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
        response.then().spec(responseSpec);

        //To log the response to report
        logResponseAsString(response);


        //Using the POJO class
        test.log(LogStatus.INFO, "Asserting of response body with the data from test data excel");
        AllureLogger.logToAllure("Asserting of response body with the data from test data excel");
        BookingDetails bookingDetails = response.as(BookingDetails.class);
        Assert.assertEquals(bookingDetails.getFirstname(), firstName);
        Assert.assertEquals(bookingDetails.getLastname(), lastName);
        Assert.assertEquals(bookingDetails.getTotalprice(), totalPrice);
        Assert.assertEquals(bookingDetails.getDepositpaid(), depositPaid);
        Assert.assertEquals(bookingDetails.getBookingdates().getCheckin(), checkIn);
        Assert.assertEquals(bookingDetails.getBookingdates().getCheckout(), checkOut);


    }
}
