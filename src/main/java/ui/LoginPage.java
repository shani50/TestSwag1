package ui;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import show.LogConstants;
import show.URLConstants;
import ui.locators.LoginLocators;
import util.UtilFactory;


public class LoginPage extends PageObject {
    private static Logger logger = LoggerFactory.getLogger(LoginPage.class);
    public WebDriver driver;
    show.ui.Common common;
    private UtilFactory utilFactory;

    public LoginPage() {
        driver = super.getDriver();
        utilFactory = new UtilFactory(driver);
    }

    @FindBy(xpath = LoginLocators.USERNAME)
    private WebElement userName;
    @FindBy(xpath = LoginLocators.PASSWORD)
    private WebElement passWord;
    @FindBy(xpath = LoginLocators.LOGIN)
    private WebElement login;

    @FindBy(xpath = LoginLocators.homePage)
    private WebElement HomePage;


    public void openUrl() {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.info(URLConstants.setUrl());
        super.openUrl(URLConstants.setUrl());
        // common.waitForPageLoaded(super.getDriver());
    }

    public void enterUserCredentials(String username, String password) {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        userName.clear();
        passWord.clear();
        utilFactory.highlight(userName);
        utilFactory.highlight(passWord);
        userName.click();
        userName.sendKeys(username);
        String a = getDriver().findElement(By.xpath(LoginLocators.USERNAME)).getText();
        System.out.println("UserPass" + a);
        passWord.click();
        passWord.sendKeys(password);
        logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());

    }

    public void getLoginButton() {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        utilFactory.highlight(login);
        login.click();
        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getMessage(String messageData) {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        utilFactory.waitForElementToBeVisible(LoginLocators.ERRORMESSAGE, "xpath");
        WebElementFacade message = find(By.xpath(LoginLocators.ERRORMESSAGE));
        utilFactory.highlight(message);
        String messageText = message.getText();
        logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
        return messageText;
    }

    public String getInvalid(String messageInvalid) {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        utilFactory.waitForElementToBeVisible(LoginLocators.alert, "xpath");
        WebElementFacade message = find(By.xpath(LoginLocators.alert));
        utilFactory.highlight(message);
        String messageText = message.getText();
        logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
        return messageText;
    }

        public void homePage(){
            logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
            HomePage.click();
            logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());

        }

}






