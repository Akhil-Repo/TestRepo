package StepDefinations;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class DeleteProducts extends Reusables{
	public static ExtentTest test = extent.createTest("Delete Product", "Trying delete a product");
	@Given("Get data from Excel {string} & {int}")
	public static void Get_data_from_Excel(String SheetName, int RowNum) {
		//clicking(Catalog);
		clicking(Products);
		Sheet= SheetName;
		RowNo= RowNum;

	}
	@When("User search Product")
	public static void User_selects_add_new_Product() throws IOException {
		driver.findElement(ProductSearchName).sendKeys(readExcel(Sheet, RowNo, "ProductName"));
	}
	@And("Select from Result & delete")
	public static void User_clicks_Basic_Toggle() throws IOException {
		try {		clicking(SearchButton);
		//clicking(CheckDelete);
		String ProductName = readExcel(Sheet, RowNo, "ProductName");
		driver.findElement(By.xpath("//td[text()='"+ProductName+"']/preceding::td[2]")).click();
		clicking(DeleteButton);
		clicking(ConfirmDelete);
		test.pass("Able delete the product");
		}
		catch (Exception e) {
			test.fail("deleting the product failed");
			e.printStackTrace();
		}
	}


	@Then("Verify Delete")
	public static void Save_the_product() throws IOException {
		driver.findElement(ProductSearchName).clear();
		driver.findElement(ProductSearchName).sendKeys(readExcel(Sheet, RowNo, "ProductName"));
		clicking(SearchButton);
		Assert.assertEquals(true, driver.findElement(SearchResult).isDisplayed());
		System.out.println("success");

	}

}

