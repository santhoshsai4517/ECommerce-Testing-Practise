package scode.ECommerceTesting.Tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import scode.ECommerce.TestComponents.BaseTest;
import scode.ECommerce.TestComponents.Retry;
import scode.ECommerceTesting.pageobjects.CartPage;
import scode.ECommerceTesting.pageobjects.ProductsPage;

public class ErrorValidations extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void productErrorValidation() throws FileNotFoundException, IOException {

		String prodName = "ZARA COAT 3";

		ProductsPage prod = login.loginApplication("santhoshsai4517@gmail.com", "151Fa04124@4517");
		prod.addProductToCart(prodName);
		CartPage cart = prod.gotoCart();
		Assert.assertFalse(cart.findProduct("ZARA COAT 30"));

	}



}
