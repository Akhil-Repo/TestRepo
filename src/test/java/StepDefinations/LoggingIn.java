package StepDefinations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoggingIn extends Reusables {
	public static ExtentTest test = extent.createTest("LoggingIn", "Trying Login");
	@Given("Launch browser")
	public static void Launch_browser() {
		startDriver();
		
		extent.setSystemInfo("TestName", "NopComAuomation");
		extent.setSystemInfo("Author","Akhil Achanta");
		extent.attachReporter(spark);
		test.pass("Browser started");
	}
	@When("User Enters URL")
	public static void User_Enters_URL() {
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		test.pass("User navigated to nopcommerce");
	}

	@And("User clicks on Login")
	public static void User_clicks_on_Login() {
		clicking(Login);
	}

	@Then("User should land on HomePage")
	public static void User_should_land_on_HomePage() {
		System.out.println(driver.getTitle());
		clicking(Catalog);
		System.out.println("Success");
		test.pass("User able to login");
	}


}
