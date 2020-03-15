package cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/grid.feature",
        glue = "cucumber.stepDefinitions.grid",
        plugin = {"pretty", "html:target/reportsGrid"},
        tags = {"@allByGrid"},
        strict = true,
        monochrome = true,
        dryRun = false)

public class RunnerGridTest {
}
