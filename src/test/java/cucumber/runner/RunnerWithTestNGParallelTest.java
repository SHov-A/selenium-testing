package cucumber.runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "classpath:features",
        glue = "cucumber.stepDefinitions",
        plugin = {"timeline:target/reportsTestNG"},
        tags = {"@all"},
        strict = true,
        monochrome = true,
        dryRun = false)

public class RunnerWithTestNGParallelTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
