package steps;


import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.SauceUtils;
import utils.SeleniumUtils;

public class AccesoPaginaSteps {

WebDriver driver; 
	
	@Before
	public void run()
	{
		driver = SauceUtils.getDriver("AccesoPagina");	
		//driver = SauceUtils.getFirefoxPortableDriver();
	}
	
	@After
	public void end()
	{
		//Cerramos el navegador
		driver.quit();
	}
	
	@When("^un usuario cualquiera accede a la aplicacion$")
	public void i_login_with_name_and_password() throws Throwable {
		//driver.get("http://localhost:8090");	
		driver.navigate().to("http://localhost:8090/");
		SeleniumUtils.textoPresentePagina(driver, "Login");

	}

	@Then("^el usuario se encuentra con la pantalla de login$")
	public void i_obtain_login_page() throws Throwable {
		SeleniumUtils.textoPresentePagina(driver, "Login");
		SeleniumUtils.textoPresentePagina(driver, "Email");
		SeleniumUtils.textoPresentePagina(driver, "Password");
	}
}
