package utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;

public class SauceUtils {

	@Value("${local.server.port}")
	protected int port;
	
	static public WebDriver getDriver(String test) {
		String sauceUser;
		String saucePassword;
		
		sauceUser = System.getenv("SAUCE_USERNAME");
		saucePassword = System.getenv("SAUCE_ACCESS_KEY");
		//sauceUser = "dashboard2b";
		//saucePassword = "176d6582-28d1-41ab-8982-97fbce376c55";
		URL saucelabs = null;
		WebDriver driver;
		
		if (sauceUser != null && saucePassword != null && !sauceUser.isEmpty() && !saucePassword.isEmpty()) {
			try {
				saucelabs = new URL("http://" + sauceUser + ":" + saucePassword + "@ondemand.saucelabs.com/wd/hub");
			} catch (MalformedURLException e) {
				System.out.println("URI Sauce mal formada");
			}

			DesiredCapabilities capabilities1 = DesiredCapabilities.firefox();
			capabilities1.setCapability("platform", "Windows 10");
			capabilities1.setCapability("version", "43.0");
			capabilities1.setCapability("tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"));
			capabilities1.setCapability("name", test);
			driver = new RemoteWebDriver(saucelabs, capabilities1);
		} else {
			driver = new FirefoxDriver();
		}
		
		return driver;
	}
	
	
	static public WebDriver getFirefoxPortableDriver() {
		 File pathToBinary = new File("src/files/firefox/FirefoxPortable.exe");
		
		 FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		 FirefoxProfile firefoxProfile = new FirefoxProfile();
		
		 return  new FirefoxDriver(ffBinary, firefoxProfile);
	}
}
