package scode.ECommerceTesting.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import scode.ECommerce.TestComponents.BaseTest;
import scode.ECommerceTesting.pageobjects.ForgotPasswordPage;
import scode.ECommerceTesting.pageobjects.LoginPage;
import scode.ECommerceTesting.pageobjects.ProductsPage;
import scode.ECommerceTesting.pageobjects.RegisterPage;

public class ForgotPasswordPageTest extends BaseTest {

	@Test()
	public void visitPage() {

		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		Assert.assertEquals(forgotPass.getHeaderText(), "Enter New Password");
		Assert.assertTrue(forgotPass.isHeaderDisplayed());

	}

	@Test(dataProvider = "getData")
	public void changePassword(HashMap<String, String> data) throws InterruptedException {
		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		LoginPage login = forgotPass.updatePassword(data.get("email"), data.get("newpassword"),
				data.get("newpassword"));
		Assert.assertEquals(login.getPasswordUpdateText(), "Password Changed Successfully");
		Thread.sleep(5000);
		ProductsPage prod = login.loginApplication(data.get("email"), data.get("newpassword"));
		Assert.assertEquals(prod.getLoginToastText(), "Login Successfully");
	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void loginWithOldPassword(HashMap<String, String> data) {

		login.loginApplication(data.get("email"), data.get("oldpassword"));
		Assert.assertEquals(login.getErrorText(), "Incorrect email or password.");

	}

	@Test(groups = { "ErrorHandling" })
	public void emptyEmail() {

		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		forgotPass.updatePassword("", "12", "12");
		Assert.assertEquals(forgotPass.getEmailErrorText(), "*Email is required");

	}

	@Test(groups = { "ErrorHandling" })
	public void invalidEmail() {

		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		forgotPass.updatePassword("S", "12", "12");
		Assert.assertEquals(forgotPass.getEmailErrorText(), "*Enter Valid Email");

	}

	@Test(groups = { "ErrorHandling" })
	public void emptyPassword() {

		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		forgotPass.updatePassword("santhoshsai4517@gmail.com", "", "12");
		Assert.assertEquals(forgotPass.getPasswordErrorText(), "*Password is required");
		Assert.assertEquals(forgotPass.getConfirmPasswordErrorText(),
				"Password and Confirm Password must match with each other.");

	}

	@Test(groups = { "ErrorHandling" })
	public void emptyConfirmPassword() {

		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		forgotPass.updatePassword("santhoshsai4517@gmail.com", "12", "");
		Assert.assertEquals(forgotPass.getConfirmPasswordErrorText(), "*Confirm Password is required");

	}

	@Test(groups = { "ErrorHandling" })
	public void passwordMismatch() {

		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		forgotPass.updatePassword("santhoshsai4517@gmail.com", "12", "123");
		Assert.assertEquals(forgotPass.getConfirmPasswordErrorText(),
				"Password and Confirm Password must match with each other.");

	}

	@Test(groups = { "ErrorHandling" })
	public void emptyFileds() {

		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		forgotPass.updatePassword("", "", "");
		Assert.assertEquals(forgotPass.getEmailErrorText(), "*Email is required");
		Assert.assertEquals(forgotPass.getPasswordErrorText(), "*Password is required");
		Assert.assertEquals(forgotPass.getConfirmPasswordErrorText(), "*Confirm Password is required");

	}

	@Test(groups = { "ErrorHandling" })
	public void notAUser() {

		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		forgotPass.updatePassword("santhosh7@gmail.com", "12", "12");
		Assert.assertEquals(forgotPass.getErrorToastText(), "User Not found.");

	}
	
	@Test
	public void gotoLogin() {
		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		LoginPage login=  forgotPass.gotoLoginPage();
		Assert.assertEquals(login.getLoginHeaderText(), "Log in");
	}
	
	@Test
	public void gotoRegister() {
		ForgotPasswordPage forgotPass = login.gotoForgotPassword();
		RegisterPage register=  forgotPass.gotoRegister();
		Assert.assertEquals(register.getRegisterHeaderText(), "Register");
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "santhoshsai@gmail.com");
		map.put("oldpassword", "151Fa04124@4517");
		map.put("newpassword", "123");
//		map.put("newpassword", "151Fa04124@4517");
//		map.put("oldpassword", "123");

		return new Object[][] { { map } };
	}
}
