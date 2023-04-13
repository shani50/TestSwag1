package ui.locators;

import java.sql.Driver;

public class LoginLocators {
    public static final String USERNAME = "//input[@id='ContentPlaceHolder1_txtUsername']";
    public static final String PASSWORD = "//input[@id='ContentPlaceHolder1_txtPassword']";
    public static final String LOGIN = "//input[@id='ContentPlaceHolder1_btnLogin']";
    public static final String homePage = "//img[@alt='Logo']";


    public static final String ERRORMESSAGE =  "//td[contains(text(),'Password Required)]";
    public static String alert = "//td[contains(text(),'Employee ID Required)]";
}


