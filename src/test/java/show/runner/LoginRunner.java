package show.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {"pretty"},features = {"C:\\Users\\Shani.Kumar\\IdeaProjects\\solution\\project\\TestSwag\\src\\test\\resources\\features"},
        glue = "stepdefs")
public class LoginRunner {

}
