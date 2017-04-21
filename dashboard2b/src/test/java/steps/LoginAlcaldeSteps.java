package steps;

import org.openqa.selenium.WebDriver;

import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;

import utils.POLoginForm;
import utils.SauceUtils;
import utils.SeleniumUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import es.uniovi.asw.Application;

@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LoginAlcaldeSteps {

	WebDriver driver;

	@Before
	public void run() {
		driver = SauceUtils.getDriver("LoginAlcalde");
		//driver = SauceUtils.getFirefoxPortableDriver();
		driver.navigate().to("http://localhost:8090/");

	}

	@After
	public void end() {
		// Cerramos el navegador
		driver.quit();
	}

	@When("^un usuario de tipo alcalde se loguea con usuario \"(.+)\" y password \"(.+)\"$")
	public void i_login_with_name_and_password_alcalde(String name, String password) throws Throwable {
		new POLoginForm().rellenaFormulario(driver, name, password);

	}

	@Then("^el usuario logueado como alcalde recibe la pantalla inicial$")
	public void i_receive_a_welcome_message_alcalde() throws Throwable {
		SeleniumUtils.textoPresentePagina(driver, "alcalde");
	}
}
