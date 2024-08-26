package scode.ECommerceTesting.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import scode.ECommerece.AbstractComponents.Utility;

public class OrdersPage extends Utility {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tbody/tr/td[2]")
	List<WebElement> productNames;

	public boolean getProductByName(String productName) {
		return productNames.stream().anyMatch(p -> p.getText().equals(productName));
	}

}
