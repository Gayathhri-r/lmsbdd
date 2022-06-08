package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;


@RunWith(Cucumber.class)

@CucumberOptions(
plugin = {"pretty", "html:target/cucumber.html"},
monochrome=true,
tags = "@feature",
features = {"src/test/resources/feature/Skill_API/SkillAPI_3_POST_Request.feature"},
glue= "stepDefinitions")

public class Testrunner extends AbstractTestNGCucumberTests{

@Override
@DataProvider(parallel = false)
public Object[][] scenarios() {
return super.scenarios();
}
}
