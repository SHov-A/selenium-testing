package service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import service.Actions;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ActionsImplTest {

    private WebDriver webDriver = new ChromeDriver();
    private Actions actions;

    private final String BASE_PATH = "http://auto.am";

    @BeforeClass
    public static void setUp() {
        //set your local system path to driver
        System.setProperty("webdriver.chrome.driver", "C://QA/chromedriver.exe");
    }

    @Before
    public void init() {
        webDriver.get(BASE_PATH);
        webDriver.manage().window().maximize();
        actions = new ActionsImpl(webDriver);
    }

    @Test
    public void testConnection() {
        actions.testConnection();
    }

    @Test
    public void checkBoundOfAmount() {
        int selectedIndex = 12;
        int upperBound = selectedIndex * 1000;
        List<Integer> integers = actions.checkBoundOfAmount(selectedIndex);
        Integer max = Collections.max(integers);

        assertTrue(upperBound >= max);
    }

    @Test
    public void searchWithAudi() {
        int[] ints = actions.searchWithAudi();

        assertEquals(ints[0], ints[1]);
    }

    @After
    public void close() {
        webDriver.close();
    }
}