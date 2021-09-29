package StepDefinations;

import org.openqa.selenium.By;

public class Locators {

	//Login
	public static By Login = By.xpath("//button[text()='Log in']");
	
	//Products
	public static By Catalog = By.xpath("(//i[@class='right fas fa-angle-left '])[1]");
	public static By Products = By.xpath("//p[text()=' Products']");
	public static By AddNew = By.xpath("//a[@href='/Admin/Product/Create']");
	public static By Toggle= By.xpath("//*[@id=\"product-form\"]/section/div/div/div/div/div/div/div/div/label/span[2]");
	public static By GiftCard = By.xpath("//div[text()='Gift card']");
	public static By ProductTab = By.xpath("//div[text()='Product info']");
	public static By ProductInfo =  By.xpath("//input[@id='Name']");
	public static By ShortDesc = By.xpath("//textarea[@name='ShortDescription']");
	public static By FullDesc = By.xpath("//body[@id='tinymce']");
	public static By Sku = By.xpath("//input[@name='Sku']");
	public static By Categories = By.xpath("//input[@class='k-input k-readonly']");
	public static By Computers = By.xpath("//li[@class='k-item k-state-focused' and text()='Computers']");
	
	//Price
	public static By PricesTab = By.xpath("//div[text()='Prices']");
	public static By PriceInfo = By.xpath("//input[@id='Price']/preceding::input[@class='k-formatted-value k-input' and contains(@title,'USD')]");
	public static By PriceTag = By.xpath("//label[text()='Price']");
	public static By TaxExempt = By.xpath("//input[@id='IsTaxExempt']");
	public static By TaxCategory = By.xpath("//select[@id='TaxCategoryId']");
	//Ship
	public static By Shiptab = By.xpath("//div[text()='Shipping']");
	public static By ShipLabel = By.xpath("//label[text()='Shipping enabled']");
	public static By Weight = By.xpath("//input[@id='Weight']/preceding::input[@class='k-formatted-value k-input' and contains(@title,'lb')]");
	public static By Length = By.xpath("//input[@id='Length']/preceding::input[@class='k-formatted-value k-input' and contains(@title,'inch')]");
	public static By Width = By.xpath("(//input[@id='Width']/preceding::input[@class='k-formatted-value k-input' and contains(@title,'inch')])[2]");
	public static By Height = By.xpath("(//input[@id='Height']/preceding::input[@class='k-formatted-value k-input' and contains(@title,'inch')])[3]");

	//Inventory
	public static By Inventory = By.xpath("//select[@id='ManageInventoryMethodId']");
	public static By InventoryLabel =By.xpath("//label[text()='Inventory method']");
	public static By InventoryTab =By.xpath("//div[text()='Inventory']");

	public static By Save = By.xpath("//button[@name='save']");

	public static By SuccessMsg = By.xpath("//div[@class='alert alert-success alert-dismissable']");

	public static By ProductSearchName = By.xpath("//input[@id='SearchProductName']");

	public static By SearchButton = By.xpath("//button[@id='search-products']");

	public static By UploadImg = By.xpath("//input[@name='qqfile']");

	public static By CheckoutResult =By.xpath("//td[text()='Laptop2']/preceding::td[2]");

	public static By DeleteButton = By.xpath("//button[@id='delete-selected']");
	
	public static By SearchResult= By.xpath("//td[text()='No data available in table']");
	
	public static By Edit =By.xpath("//a[contains(@href,'Edit')]");
	
	public static By ConfirmDelete = By.xpath("//button[@id='delete-selected-action-confirmation-submit-button']");

	public static By ProductPic =By.xpath("//button[text()='Add product picture']");
	
	public static By ImageTitle = By.xpath("//img[contains(@src,'Test')]");
	
	public static By Image = By.xpath("//img[contains(@src,'laptop2')]");

	








}
