
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given; //import this

import io.restassured.response.Response;
import pojoClasses.BookingDates;
import pojoClasses.BookingDetails;
import utility.AllureLogger;
import utility.BaseTest;
import utility.ExcelLib;
import utility.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class CreateBooking extends BaseTest {

    public static String newID = "";

    @DataProvider(name = "DataFromExcel")
    public Object[][] data() throws Exception {
        ExcelLib xl = new ExcelLib("Booking", this.getClass().getSimpleName());
        return xl.getTestdata();
    }

    @Test(dataProvider = "DataFromExcel", description = "To retrieve the details of the booking IDs")
    public void createNewBooking(String firstName,
                                 String lastName,
                                 String totalPrice,
                                 String depositPaid,
                                 String checkIn,
                                 String checkOut,
                                 String additionalNeeds, String dontUseThis
    ) {
        test.log(LogStatus.INFO, "Starting the test to create new details");
        /*
         * Send a POST request to /booking/{id}
         * and check that the response has HTTP status code 200
         */
        //Sending the GET request for a specific booking id and receiving the response

        test.log(LogStatus.INFO, "Posting a new booking detail");
        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setFirstname(firstName);
        bookingDetails.setLastname(lastName);
        bookingDetails.setTotalprice(Integer.parseInt(totalPrice));
        bookingDetails.setDepositpaid(Boolean.parseBoolean(depositPaid));
        bookingDetails.setAdditionalneeds(additionalNeeds);
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkIn);
        bookingDates.setCheckout(checkOut);
        bookingDetails.setBookingdates(bookingDates);
        test.log(LogStatus.INFO, "Sending the POST request to create new booking");
        Response response = given().
                spec(requestSpec).
                contentType("application/json").
                body(bookingDetails).log().body().
                when().
                post("/booking");

        //Verify the response code
        test.log(LogStatus.INFO, "Asserting the response if the status code returned is 200");
        response.then().spec(responseSpec);

        //To log the response to report
        logResponseAsString(response);

        //To get the newly created booking id
        newID = response.then().extract().path("bookingid").toString();
        test.log(LogStatus.PASS, "Retrieved booking id : " + response.then().extract().path("bookingid"));

    }
}
