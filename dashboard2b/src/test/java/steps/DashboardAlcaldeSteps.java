package steps;

import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.POLoginForm;
import utils.SauceUtils;
import utils.SeleniumUtils;

public class DashboardAlcaldeSteps {
	WebDriver driver;

	@Before
	public void run() {
		driver = SauceUtils.getDriver("DashboardAlcalde");
		//driver = SauceUtils.getFirefoxPortableDriver();
		driver.navigate().to("http://localhost:8090/");

	}

	@After
	public void end() {
		// Cerramos el navegador
		driver.quit();
	}

	@When("^el alcalde se loguea con usuario \"(.+)\" y password \"(.+)\"$")
	public void i_login_with_name_and_password_alcalde_dashboard(String name, String password) throws Throwable {
		new POLoginForm().rellenaFormulario(driver, name, password);

	}

	@Then("^el alcalde se encuentra con el dashboard para alcalde$")
	public void i_receive_dashboard_alcalde() throws Throwable {
		SeleniumUtils.EsperaCargaPagina(driver, "id", "tablaAlcalde", 5);
		assertTrue(SeleniumUtils.EsperaCargaPagina(driver, "id", "tablaAlcalde", 5).get(0) != null);
	}
}
