package service.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionsImpl implements Actions {

    private final WebDriver webDriver;

    private final String BRAND = "//select[@id='filter-make']";
    private final String MODEL = "//select[@id='v-model']";
    private final String FROM_DATE = "//select[@name='year[gt]']";
    private final String TO_DATE = "//select[@name='year[lt]']";
    private final String PRICE_START = "//select[@name='usdprice[gt]']";
    private final String PRICE_END = "//select[@name='usdprice[lt]']";
    private final String SEARCH = "//*[@id='search-btn']";

    public ActionsImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void testConnection() {
        List<String> comboBoxes = Arrays.asList(BRAND, MODEL, FROM_DATE, TO_DATE, PRICE_START, PRICE_END);
        for (String xpath : comboBoxes) {
            new Select(new WebDriverWait(webDriver, 10)
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))).selectByIndex((int) (1 + Math.random() * 10));
        }
    }

    @Override
    public List<Integer> checkBoundOfAmount(int selectedIndex) {
        Select select = new Select(webDriver.findElement(By.xpath(PRICE_END)));
        select.selectByIndex(selectedIndex);
        select.getOptions().get(selectedIndex).getText();
        webDriver.findElement(By.xpath(SEARCH)).click();
        WebElement searchResult = webDriver.findElement(By.xpath("//*[@id='search-result']"));
        List<WebElement> elements = searchResult.findElements(By.xpath(".//div[@class='price bold blue-text']/span"));
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return checkList(texts);
    }

    @Override
    public int[] searchWithAudi() {
        WebElement search = webDriver.findElement(By.xpath(BRAND));
        search.sendKeys("Audi", Keys.ENTER);
        webDriver.findElement(By.xpath(SEARCH)).click();
        WebElement element = webDriver.findElement(By.xpath("//div[@class='search-result-info alert alert-success large']"));
        String text = element.getText();
        Pattern compile = Pattern.compile("([0-9]+)");
        Matcher matcher = compile.matcher(text);
        int totalCount = 0;
        if (matcher.find()) {
            totalCount = Integer.parseInt(matcher.group());
        }
        WebElement searchResult = webDriver.findElement(By.xpath("//*[@id='search-result']"));
        int actualCount = 0;
        final int ITEMS_PER_PAGE = 50;
        int countOfPages = totalCount <= ITEMS_PER_PAGE ? 1 : totalCount / ITEMS_PER_PAGE + 1;
        for (int i = 0; i < countOfPages; i++) {
            actualCount += searchResult.findElements(By.xpath(".//div[@class='card-content']")).size();
            new WebDriverWait(webDriver, 40)
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(text(),'chevron_right')]")))
                    .click();
        }
        int[] results = new int[2];
        results[0] = totalCount;
        results[1] = actualCount;
        return results;
    }

    private List<Integer> checkList(List<String> list) {
        List<Integer> prices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (str.charAt(0) == '֏') {
                list.set(i, convertToDollar(str));
            } else {
                list.set(i, removeSpaces(str));
            }
            prices.add(Integer.parseInt(list.get(i)));
        }
        return prices;
    }

    private String convertToDollar(String el) {
        String s = removeSpaces(el);
        return String.valueOf(Integer.parseInt(s) / 480);
    }

    private String removeSpaces(String str) {
        return str.substring(1).replaceAll(" ", "");
    }
}
