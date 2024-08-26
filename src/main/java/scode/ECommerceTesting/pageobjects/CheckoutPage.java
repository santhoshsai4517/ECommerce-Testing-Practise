package scode.ECommerceTesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import scode.ECommerece.AbstractComponents.Utility;

public class CheckoutPage extends Utility {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='field small']/select[1]")
	WebElement monthElm;
	
	@FindBy(xpath = "//div[@class='field small']/select[2]")
	WebElement yearElm;
	
	@FindBy(xpath = "(//input[@class='input txt'])[1]")
	WebElement cvvElm;
	
	@FindBy(xpath = "(//input[@class='input txt'])[2]")
	WebElement nameElm;
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement countryElm;
	
	@FindBy(css = "button[class*='ng-star-inserted']:nth-child(3)")
	WebElement countrySelectElm;

	@FindBy(css = "a[class*='action__submit']")
	WebElement submit;
	
	public OrderConfirmationPage placeOrder(String month,String year,String cvv,String name,String country) {
		Select monthSelect = new Select(monthElm);
		Select yearSelect = new Select(yearElm);
		
		monthSelect.selectByVisibleText(month);
		yearSelect.selectByVisibleText(month);
		cvvElm.sendKeys(cvv);
		nameElm.sendKeys(name);
		countryElm.sendKeys(country);
		countrySelectElm.click();
		submit.click();
		
		return new OrderConfirmationPage(driver);
	}
	
}
