package cucumber.stepDefinitions.grid;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import service.Actions;
import service.impl.ActionsImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class StepDefinitionGrid {
    private final int selectedIndex = 12;
    private final String BASE_PATH = "http://auto.am";
    private int max;
    private int[] ints;
    private Actions actions;
    private WebDriver webDriver = getRemoteWebDriver();

    @Given("Go to auto.am page using Selenium grid")
    public void go_to_auto_am_page_using_Selenium_grid() {
        webDriver.get(BASE_PATH);
        webDriver.manage().window().maximize();
        actions = new ActionsImpl(webDriver);
    }

    @When("Entering values in the combo boxes using Selenium grid")
    public void entering_values_in_the_combo_boxes_using_Selenium_grid() {
        actions.testConnection();
    }

    @When("Selecting index of upper bound taking all prices using Selenium grid")
    public void selecting_index_of_upper_bound_taking_all_prices_using_Selenium_grid() {
        List<Integer> integers = actions.checkBoundOfAmount(selectedIndex);
        max = Collections.max(integers);
    }

    @Then("All prices in result are smaller than selected upper bound using Selenium grid")
    public void all_prices_in_result_are_smaller_than_selected_upper_bound_using_Selenium_grid() {
        int upperBound = selectedIndex * 1000;
        assertTrue(upperBound >= max);
    }

    @When("Filter by Audi taking actual items count using Selenium grid")
    public void filter_by_Audi_taking_actual_items_count_using_Selenium_grid() {
        ints = actions.searchWithAudi();
    }

    @Then("Actual items count matches with the number in green box using Selenium grid")
    public void actual_items_count_matches_with_the_number_in_green_box_using_Selenium_grid() {
        assertEquals(ints[0], ints[1]);
    }

    @After
    public void closePage() {
        webDriver.quit();
    }

    private WebDriver getRemoteWebDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        //use your hub ip
        String hubUrl = "http://172.27.200.9:5555/wd/hub";
        //WebDriver webDriver = null;
        try {
            webDriver = new RemoteWebDriver(new URL(hubUrl), capabilities);
        } catch (MalformedURLException e) {
            System.err.println("please input valid url " + e.getMessage());
        }
        return webDriver;
    }
}
