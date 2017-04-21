package steps;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.POLoginForm;
import utils.SauceUtils;
import utils.SeleniumUtils;

import static org.junit.Assert.assertTrue;

public class LoginConcejalSteps {

	WebDriver driver;

	@Before
	public void run() {
		driver = SauceUtils.getDriver("LoginConcejal");
		//driver = SauceUtils.getFirefoxPortableDriver();
		driver.navigate().to("http://localhost:8090/");
		
	}

	@After
	public void end() {
		// Cerramos el navegador
		driver.quit();
	}

	@When("^un usuario de tipo concejal se loguea con usuario \"(.+)\" y password \"(.+)\"$")
	public void i_login_with_name_and_password_concejal(String name, String password) throws Throwable {
		new POLoginForm().rellenaFormulario(driver, name, password);
	}

	@Then("^el usuario logueado como concejal recibe la pantalla inicial$")
	public void i_receive_a_welcome_message_concejal() throws Throwable {
		SeleniumUtils.textoPresentePagina(driver, "Vista de concejal");
	}
	

}