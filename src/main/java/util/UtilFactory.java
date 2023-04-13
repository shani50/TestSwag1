package util;

import com.ibm.icu.impl.Assert;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.pages.PageObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import show.LogConstants;
import show.ResourcePathConstants;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class UtilFactory extends PageObject {

    public WebDriver driver;


    public String getFeatureName(Scenario scenario) {
        String featureFilePath = String.valueOf(scenario.getUri());
        return featureFilePath.substring((featureFilePath.lastIndexOf('/') + 1), featureFilePath.indexOf('.'));
    }

    private static Logger logger = LoggerFactory.getLogger(UtilFactory.class);
    DesiredCapabilities capability = new DesiredCapabilities();

    public UtilFactory(WebDriver driver) {
        driver = super.getDriver();
    }

    public void waitForElementToBeVisible(String webElement, String elementType) {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
        if (elementType.equals("xpath")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElement)));
        } else if (elementType.equals("css")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(webElement)));
        } else if (elementType.equals("id")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(webElement)));
        }
        logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void highlight(WebElement element) {
        driver = super.getDriver();
        executeScript(driver, "arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid red;");
    }

    public Object executeScript(WebDriver driver, String javascriptToExecute, Object... parameters) {
        return ((JavascriptExecutor) driver).executeScript(javascriptToExecute, parameters);
    }


    public String readJSON(String featureName, String scenarioNo, String key, String fileName) throws ParseException, IOException {
        logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
        JSONParser parser = new JSONParser();
        try {
            String dataFileEnvPath = ResourcePathConstants.JSON_DATA_FILE_PATH + System.getProperty("env");
            String dataFilePath = dataFileEnvPath + "/" + fileName;
            File dataFile = new File(dataFilePath);
            if (!dataFile.exists()) {
                logger.error("Given environment data file not found at location = " + dataFilePath);
                Assert.fail("Given environment data file not found at location=" + dataFilePath);
            }
            Object objectFromDataFile = parser.parse(new FileReader(dataFilePath));
            JSONObject jsonObjectFromDataFile = (JSONObject) objectFromDataFile;
            JSONObject featureObject = (JSONObject) jsonObjectFromDataFile.get(featureName);
            System.out.println("featureObject----->" + featureObject);
            JSONObject scenarioObject = (JSONObject) featureObject.get(scenarioNo);
            logger.info("Feature Name: " + featureName + " Scenario No: " + scenarioNo + " Key: " + key
                    + " value returned: " + (String) scenarioObject.get(key));
            logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
            return (String) scenarioObject.get(key);
        } catch (FileNotFoundException e) {
            logger.error(LogConstants.FILE_NOT_FOUND_EXCEPTION + e
                    + Thread.currentThread().getStackTrace()[1].getMethodName());
        }
        logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
        return "Error while getting json value";
    }



}
