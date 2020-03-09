package cucumber.runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
        glue = "cucumber.stepDefinitions",
        plugin = {"pretty", "html:target/reportsJunit"},
        tags = {"@all"},
        strict = true,
        monochrome = true,
        dryRun = false)

public class RunnerTest {
}
