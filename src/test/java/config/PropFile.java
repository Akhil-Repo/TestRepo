package config;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import StepDefinations.Locators;

public class PropFile extends Locators {
	static WebDriver driver=null;
	static 	Properties obj = new Properties();
	static	String projectpath= System.getProperty("user.dir");
	public static void main(String[] args) {
		System.setProperty("WebDriver.Chrome.Driver", "C:/chromedriver_win32/chromedriver.exe");
	driver=new ChromeDriver();
	}	
	public static String getProperties(String locator) throws FileNotFoundException, IOException{

		InputStream objfile = new FileInputStream(projectpath+"/src/test/java/config/Properties.properties");
		obj.load(objfile);
		String xpath = obj.getProperty(locator);
		return xpath;

	}
	public static void setProperties(){
		try {


			OutputStream objfile = new FileOutputStream(projectpath+"/src/test/java/config/Properties.properties");

			obj.setProperty("browser", "Chrome");
			obj.store(objfile, null);
		} 

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}



}
