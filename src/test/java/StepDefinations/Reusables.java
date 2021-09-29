package StepDefinations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.*;
import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.poi.ss.usermodel.DataFormatter;

public class Reusables extends Locators {

	public static WebDriver driver;

	public static String Sheet;
	public static int RowNo;
	public static Properties obj = new Properties();
	public static String projectpath = System.getProperty("user.dir");
	public static ExtentReports extent = new ExtentReports();
	public static ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");


	public static void main(String[] args) {

	}


	public static void startDriver() {

		System.setProperty("webdriver.chrome.driver",projectpath + "/src/test/resources/Driver/chromedriver.exe"); 
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 

	}
	public static void screenshot(WebDriver driver, String ScreenShotName) {
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot,new File("C:/Users/Akhil/git/MyRepo/target/screenShot/"+ScreenShotName+".png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}


//	public static void ScreenShotByte(WebDriver driver, String ScreenShotName) throws IOException {
//
//	 File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	 String path=System.getProperty("D:/Selenium/JavaCucu/target/screenShotByte/"+ScreenShotName+".png"); FileUtils.copyFile(screenshot,new File(path)); 
//	 byte[]imageBytes= IOUtils.toByteArray(new FileInputStream(path)); 
//	 return Base64.getEncoder().encodeToString(imageBytes);




	public static String getProperties(String locator) throws FileNotFoundException, IOException {

		InputStream objfile = new FileInputStream(projectpath + "/src/test/java/config/Properties.properties");
		obj.load(objfile);
		String xpath = obj.getProperty(locator);
		System.out.print(xpath);
		return xpath;

	}

	public static void setProperties() {
		try {

			OutputStream objfile = new FileOutputStream(projectpath + "/src/test/java/config/Properties.properties");

			obj.setProperty("browser", "Chrome");
			obj.store(objfile, null);
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void mouseOver(WebDriver driver, By Element) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(Element)).perform();
	}



	public static String readExcel(String Sheet, int Row, String Field) throws IOException {
		String path=(projectpath+"/target/Data/testdata.xlsx");
		File file = new File(path);

		FileInputStream input = new FileInputStream(file);

		Workbook newWorkBook = null;

		newWorkBook = new XSSFWorkbook(input);

		Sheet newSheet = newWorkBook.getSheet(Sheet);

		int RowCount = newSheet.getLastRowNum()-newSheet.getFirstRowNum();
		Row OneRow = newSheet.getRow(0);
		int ColumnNo = OneRow.getPhysicalNumberOfCells();
		Cell option;
		Cell value;
		String data;
		String inputdata = null;
		DataFormatter formatter = new DataFormatter();
		for (int i=0; i<ColumnNo;i++) {



			option = newSheet.getRow(0).getCell(i);
			data= formatter.formatCellValue(option);
			if(data.equalsIgnoreCase(Field)) {

				value=newSheet.getRow(Row).getCell(i);
				inputdata= formatter.formatCellValue(value);

			}


		}
		input.close();
		newWorkBook.close();
		return inputdata;

	}

	public static void readTextFile(String path) throws IOException {

		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file));

		String lines;
		int count=0;
		while ((lines = br.readLine()) != null) {
			char[] ch=lines.toCharArray();
			for(Character c:ch) {
				if(c.equals(' ')){
					count++;
				}

			}

		}
		System.out.println("number of spaces:" +count);
		br.close();} 

	public static void clicking(By Locator) {

		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(Locator)).click();

	}
	
	public static Boolean IsElementDisplayed(By Locator) {

		Boolean flag=driver.findElement(Locator).isDisplayed();

		System.out.println("Is Element Present On Page: "+flag);

		return flag;
	}
	
	public static void writeExcel(List<WebElement> list) throws IOException {
		
		FileInputStream filein = new FileInputStream(projectpath+"/target/Data/dataRetrieved.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(filein);
		XSSFSheet Writesheet=null;Writesheet = workbook.getSheet("TestData");
		Row row = Writesheet.createRow(1); 

		for(int i=0;i<list.size();i++)
		{
			Cell cell = row.createCell(i);
			cell.setCellValue(list.get(i).getText());

			System.out.println(list.get(i).getText());
		}


		FileOutputStream fileout = new FileOutputStream(projectpath+"/target/Data/dataRetrieved.xlsx");
		workbook.write(fileout);
		fileout.close();

	}
}
