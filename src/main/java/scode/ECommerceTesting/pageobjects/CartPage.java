package scode.ECommerceTesting.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import scode.ECommerece.AbstractComponents.Utility;

public class CartPage extends Utility {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutBtn;
	
	
	public boolean findProduct(String productName)
	{
		return cartProducts.stream().anyMatch(p -> p.getText().equals(productName));
	}
	
	public CheckoutPage checkout() {
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}
}
