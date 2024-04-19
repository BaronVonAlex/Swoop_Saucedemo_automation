package ge.tbcacad.util.swooputil;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import ge.tbcacad.pages.swoop.SwoopHolidayPage;
import ge.tbcacad.steps.swoop.SwoopHolidaySteps;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.devtools.v85.page.Page;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;


public class SwoopOffersUtil {
    public static List<Double> extractPricesFromElements(ElementsCollection elements) {
        SwoopHolidayPage swoopHolidayPage = new SwoopHolidayPage();

        List<Double> offerPrices = new ArrayList<>();
        Set<Double> uniquePrices = new HashSet<>(); // Temporary set to store unique prices

        swoopHolidayPage.lastPage.click();
        String currentURL = WebDriverRunner.url();
        int pageNumber = extractPageNumber(currentURL);

        // Iterate through each page
        for (int i = 1; i <= pageNumber; i++) {
            // Extract prices from the current page and add them to the temporary set
            offerPrices.addAll(extractPrices(Collections.singletonList(elements)));
            uniquePrices.addAll(offerPrices);

            // Go to the previous page
            if (i < pageNumber) {
                try {
                    swoopHolidayPage.previousPageButton.click();
                } catch (ElementClickInterceptedException e) {
                    // Handle the ElementClickInterceptedException if needed
                    System.err.println("Element click intercepted. Exiting loop.");
                    break;
                }
            }
        }

        // Clear offerPrices and add unique prices from the set
        offerPrices.clear();
        offerPrices.addAll(uniquePrices);

        return offerPrices;
    }

    private static double extractPriceFromString(String priceText) {
        // Regular expression to extract numbers from string
        Pattern pattern = Pattern.compile("\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(priceText);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group());
        } else {
            return -1; // Return -1 if no valid price found
        }
    }

    public static List<Double> extractPrices(List<ElementsCollection> elementsList) {
        List<Double> prices = new ArrayList<>();

        for (ElementsCollection elements : elementsList) {
            for (int i = 0; i < elements.size(); i++) {
                try {
                    String priceText = elements.get(i).getText().replaceAll("[^0-9.]", "");
                    if (!priceText.isEmpty()) { // Skip empty strings
                        double price = Double.parseDouble(priceText);
                        prices.add(price);
                    }
                } catch (StaleElementReferenceException e) {
                    // Handle StaleElementReferenceException here
                    System.err.println("ERR: " + e.getMessage());
                } catch (NumberFormatException e) {
                    // Handle NumberFormatException here
                    System.err.println("NUMBER_FORMAT_EXCEPTION_MSG: " + e.getMessage());
                }
            }
        }
        return prices;
    }

    public static int extractPageNumber(String url) {
        // Define a regex pattern to match the page number
        Pattern pattern = Pattern.compile("page=(\\d+)");
        Matcher matcher = pattern.matcher(url);

        // Find the last occurrence of the page number in the URL
        int pageNumber = 0;
        while (matcher.find()) {
            pageNumber = Integer.parseInt(matcher.group(1));
        }
        return pageNumber;
    }
}
