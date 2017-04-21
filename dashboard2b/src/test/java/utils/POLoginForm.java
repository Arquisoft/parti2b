package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class POLoginForm {

	public void rellenaFormulario(WebDriver driver, String puser,
			String ppassword) throws InterruptedException {

		WebElement user;
		WebElement password;

		user = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"email", 5).get(0);

		user.click();
		user.clear();

		user.sendKeys(puser);

		password = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"password", 0).get(0);

		password.click();
		password.clear();
		password.sendKeys(ppassword);

		SeleniumUtils.EsperaCargaPagina(driver, "id",
				"btnLogin", 0).get(0).click();
		Thread.sleep(1000);

	}
}
