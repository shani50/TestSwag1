package stepdefs;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Managed;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import show.FeatureNameConstants;
import show.LogConstants;
import ui.LoginPage;
import util.UtilFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class LoginStepDef extends PageObject {
    private static Logger logger = LoggerFactory.getLogger(LoginStepDef.class);
    @Managed
    public WebDriver driver;
    LoginPage loginPageBusinessLogic;
    UtilFactory utilFactory;


    public LoginStepDef() {
        driver = super.getDriver();
        new UtilFactory(driver);
    }

    @Given("User is on the Srkay  home page")
    public void navigateToLink() {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.info("User is on home page");
        loginPageBusinessLogic.openUrl();
        logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @When("User enters username and password")
    public void clickOnLogin(List<List<String>> loginCredential) throws ParseException, IOException {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        loginPageBusinessLogic.enterUserCredentials(utilFactory.readJSON(FeatureNameConstants.LOGIN, loginCredential.get(0).get(0), "username",
                        "Login.json"),
                utilFactory.readJSON(FeatureNameConstants.LOGIN, loginCredential.get(0).get(0), "password",
                        "Login.json"));
        logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());

    }

    @And("User click the login button")
    public void loginClick() {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        loginPageBusinessLogic.getLoginButton();
        logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Then("User gets alert message")
    public void alertMessage(List<List<String>> message) throws ParseException, IOException {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.info("Scenario Name  :: " + message.get(0).get(0));
        String a = message.get(0).get(0);
        if (a.equals("alert_message")) {
            String expectedMessage = utilFactory.readJSON(FeatureNameConstants.LOGIN, message.get(0).get(0),
                    "message", "Login.json");
            assertEquals(expectedMessage, loginPageBusinessLogic.getMessage(expectedMessage));
        } else if(a.equals("Invalid")) {
            String expectedMessage = utilFactory.readJSON(FeatureNameConstants.LOGIN, message.get(0).get(0),
                    "message", "Login.json");
            assertEquals(expectedMessage, loginPageBusinessLogic.getInvalid(expectedMessage));
        }
        logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());

        }
        @Then("User should successfully login")
        public void successLogin() {
            logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
            loginPageBusinessLogic.homePage();
            logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());

        }


    }



