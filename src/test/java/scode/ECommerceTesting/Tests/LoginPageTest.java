package scode.ECommerceTesting.Tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import scode.ECommerce.TestComponents.BaseTest;
import scode.ECommerce.TestComponents.Retry;
import scode.ECommerceTesting.pageobjects.CartPage;
import scode.ECommerceTesting.pageobjects.CheckoutPage;
import scode.ECommerceTesting.pageobjects.OrderConfirmationPage;
import scode.ECommerceTesting.pageobjects.ProductsPage;

public class LoginPageTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void loginValidation(HashMap<String, String> data) throws FileNotFoundException, IOException {

		ProductsPage prod = login.loginApplication(data.get("email"), data.get("password"));
		Assert.assertEquals(prod.getLoginToastText(), "Login Successfully");
		Assert.assertTrue(prod.isSignoutVisible());

	}

	@Test(groups = { "ErrorHandling" })
	public void incorrecCredentials() {

		login.loginApplication("santhoshsai4517@gmail.com", "123");
		Assert.assertEquals(login.getErrorText(), "Incorrect email or password.");

	}

	@Test(groups = { "ErrorHandling" })
	public void emptyEmail() {

		login.loginApplication("", "123");
		Assert.assertEquals(login.getEmailErrorText(), "*Email is required");

	}

	@Test(groups = { "ErrorHandling" })
	public void emptyPassword() {

		login.loginApplication("santhoshsai4517@gmail.com", "");
		Assert.assertEquals(login.getPasswordErrorText(), "*Password is required");

	}
	
	@Test(groups = { "ErrorHandling" })
	public void emptyEmailAndPassword() throws InterruptedException {

		login.loginApplication("", "");
//		Thread.sleep(5000);
//		System.out.println(driver.findElements(By.cssSelector("div[class='ng-star-inserted']")).size());
		Assert.assertTrue(login.areErrorsPresent("*Email is required","*Password is required") );

	}
	

	@DataProvider
	public Object[][] getData() throws IOException {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "santhoshsai4517@gmail.com");
		map.put("password", "151Fa04124@4517");
		map.put("prodName", "ZARA COAT 3");

//		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\scode\\ECommerece\\Data\\PurchaseOrder.json");
		return new Object[][] { { map } };
	}
}
