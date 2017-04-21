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

public class DashboardConcejalSteps {

	WebDriver driver;

	@Before
	public void run() {
		driver = SauceUtils.getDriver("DashboardConcejal");
		//driver = SauceUtils.getFirefoxPortableDriver();
		driver.navigate().to("http://localhost:8090/");

	}

	@After
	public void end() {
		// Cerramos el navegador
		driver.quit();
	}

	@When("^el concejal se loguea con usuario \"(.+)\" y password \"(.+)\"$")
	public void i_login_with_name_and_password_concejal_dashboard(String name, String password) throws Throwable {
		new POLoginForm().rellenaFormulario(driver, name, password);

	}

	@Then("^el concejal se encuentra con el dashboard para concejal$")
	public void i_receive_dashboard_concejal() throws Throwable {
		SeleniumUtils.textoPresentePagina(driver, "Vista de concejal");
		assertTrue(SeleniumUtils.EsperaCargaPagina(driver, "id", "tablaConcejal", 5).get(0) != null);
	}
}
