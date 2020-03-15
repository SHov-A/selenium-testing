package cucumber.runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "classpath:features/grid.feature",
        glue = "cucumber.stepDefinitions.grid",
        plugin = {"timeline:target/reportsTestNGGrid"},
        tags = {"@allByGrid"},
        strict = true,
        monochrome = true,
        dryRun = false)

public class RunnerWithTestNGParallelGridTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
