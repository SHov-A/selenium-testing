package cucumber.stepDefinitions.gherkin;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import service.Actions;
import service.impl.ActionsImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class StepDefinition {

    static {
        //set your local system path to driver
        System.setProperty("webdriver.chrome.driver", "C://QA/chromedriver.exe");
    }

    private final int selectedIndex = 12;
    private final String BASE_PATH = "http://auto.am";
    private int max;
    private int[] ints;
    private WebDriver webDriver = new ChromeDriver();
    private Actions actions;

    @Given("Go to auto.am page")
    public void go_to_auto_am_page() {
        webDriver.get(BASE_PATH);
        webDriver.manage().window().maximize();
        actions = new ActionsImpl(webDriver);
    }

    @When("Entering values in the combo boxes")
    public void entering_values_in_the_combo_boxes() {
        actions.testConnection();
    }

    @When("Selecting index of upper bound taking all prices")
    public void selecting_index_of_upper_bound_taking_all_prices() {
        List<Integer> integers = actions.checkBoundOfAmount(selectedIndex);
        max = Collections.max(integers);
    }

    @Then("All prices in result are smaller than selected upper bound")
    public void all_prices_in_result_are_smaller_than_selected_upper_bound() {
        int upperBound = selectedIndex * 1000;
        assertTrue(upperBound >= max);
    }

    @When("Filter by Audi taking actual items count")
    public void filter_by_Audi_taking_actual_items_count() {
        ints = actions.searchWithAudi();
    }

    @Then("Actual items count matches with the number in green box")
    public void actual_items_count_matches_with_the_number_in_green_box() {
        assertEquals(ints[0], ints[1]);
    }

    @After
    public void closePage() {
        webDriver.quit();
    }
}
