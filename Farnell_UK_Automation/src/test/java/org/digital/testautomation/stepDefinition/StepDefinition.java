package org.digital.testautomation.stepDefinition;

import org.digital.testautomation.manager.BrowserManager;
import org.digital.testautomation.pageobjects.LandingPage;
import org.digital.testautomation.pageobjects.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * 
 * @author ANKIT THAKUR
 * This class will contain the step definition of the cucumber steps
 */

public class StepDefinition extends BrowserManager{
	
	WebDriver driver=null;
	LandingPage landingPage; 
	RegistrationPage registrationPage;
	protected static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(StepDefinition.class);
	
	@Given("^user launches browser in \"([^\"]*)\"$")
	public void user_launches_browser_in(String browserName) throws Throwable {
		driver = BrowserManager.initialization(browserName);
		logger.info("Browser is launched");
	}
	
	@And("^user accepts cookie policy$")
	public void user_accepts_cookie_policy() throws Throwable {
		landingPage = new LandingPage(driver);
		landingPage.accept_Cookie();
		logger.info("User has successfully accepted Browser Cookies");
	}
	
	@And("^user verify landing page is displayed$")
	public void verify_Landing_Page_Is_Displayed() throws Throwable {
		String PageTitle = landingPage.verify_Landing_Page_Is_Displayed();
		Assert.assertTrue(PageTitle.equalsIgnoreCase("Farnell"), "Landing Page is not displayed");
		logger.info("Landing page is displayed");
	}
	
	@And("^user click on the Register link$")
	public void user_click_on_the_register_link() throws Throwable {
		landingPage.click_On_Register_Link();
		logger.info("User has successfully clicked on Register Link");
	}
	
	@Then("^verify title of the Registration page$")
	public void user_verify_title_of_the_page() throws Throwable {
		Boolean title = registrationPage.verify_Registration_Page_Is_Displayed(); 
		Assert.assertTrue(title, "Registration page is not displayed");
		logger.info("Registration page is displayed");
	}
	
	@And("^user enter \"([^\"]*)\" and \"([^\"]*)\" on the page$")
	public void user_enter_username_and_password_on_the_registration_page(String username, String password) throws Throwable {
		registrationPage = new RegistrationPage(driver);
		registrationPage.enter_Credentials(username, password);
		logger.info("User has successfully entered username and password details");
	}
	
	@And("^user enters \"([^\"]*)\" and \"([^\"]*)\" on the page$")
	public void user_enter_further_details_on_the_registration_page(String fullname, String email) throws Throwable {
		registrationPage.enter_FullName(fullname);
		registrationPage.enter_Email(email);
		logger.info("User has successfully entered fullname and email details");
	}
	
	@And("^user verify whether Remember is already selected$")
	public void user_verify_whether_Remember_is_already_selected() throws Throwable {
	    Boolean isChecked = registrationPage.verify_Remember_Me_is_selected(); 
	    Assert.assertTrue(isChecked, "Remember Me is not selected");
	    logger.info("Remember Me is already selected");
	}
	
	@And("^user click on the Register button$")
	public void user_click_on_the_button() throws Throwable {
		registrationPage.click_on_Register_button();
		logger.info("User has successfully clicked on Register button");
	}
	
	@And("^user verify whether Remember is not selected$")
	public void user_verify_whether_Remember_is_not_selected() throws Throwable {
	    Boolean isChecked = registrationPage.verify_Remember_Me_is_selected(); 
	    Assert.assertFalse(isChecked, "Remember Me is already selected");
	    logger.info("Remember Me is not selected");
	}
	
	@And("^user click on the checkbox Remember Me$")
	public void user_click_on_the_checkbox() throws Throwable {
		registrationPage.click_on_Remember_Me_checkbox();
		logger.info("User has successfully clicked on Remember Me checkbox");
	}
	
	@Then("^verify user is successfully logged$")
	public void verify_user_is_successfully_logged() throws Throwable {
		String username =  registrationPage.verify_username_after_registration();
		Assert.assertTrue(username.equalsIgnoreCase("Ankit"),"User name is incorrect");
		logger.info("User has successfully logged In");
	}
	
	@Then("^throw successful logged in alert$")
	public void throw_successful_logged_in_alert() throws Throwable {
		landingPage.triggerAlert("You are successfully logged in");
	    logger.info("User has been displayed with alert message of successful login");
	}
	
	@Then("^close browser$")
	public void close_browser() throws Throwable {
		BrowserManager.closeAllBrowser();
		logger.info("Browser is closed");
	}
	
}
