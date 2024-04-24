package ge.tbcacad.util.swooputil;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import ge.tbcacad.pages.swoop.SwoopHolidayPage;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwoopOffersUtil {
    public static List<Double> extractPricesFromElements(ElementsCollection elements) {
        SwoopHolidayPage swoopHolidayPage = new SwoopHolidayPage();

        List<Double> offerPrices = new ArrayList<>();
        Set<Double> uniquePrices = new HashSet<>();

        if (swoopHolidayPage.lastPageButton.exists()) {
            swoopHolidayPage.lastPageButton.click();
            String currentURL = WebDriverRunner.url();
            int pageNumber = extractPageNumber(currentURL);

            for (int i = 1; i <= pageNumber; i++) {
                offerPrices.addAll(extractPrices(Collections.singletonList(elements)));
                uniquePrices.addAll(offerPrices);

                if (i < pageNumber) {
                    try {
                        swoopHolidayPage.previousPageButton.click();
                    } catch (ElementClickInterceptedException e) {
                        System.err.println("Element click intercepted. Exiting loop.");
                        break;
                    }
                }
            }
        } else {
            offerPrices.addAll(extractPrices(Collections.singletonList(elements)));
            uniquePrices.addAll(offerPrices);
        }
        offerPrices.clear();
        offerPrices.addAll(uniquePrices);

        return offerPrices;
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
                    System.err.println("StaleElementRefernce: " + e.getMessage());
                } catch (NumberFormatException e) {
                    // Handle NumberFormatException here
                    System.err.println("NumberFormatException: " + e.getMessage());
                }
            }
        }
        return prices;
    }

    public static int extractPageNumber(String url) {
        Pattern pattern = Pattern.compile("page=(\\d+)");
        Matcher matcher = pattern.matcher(url);

        int pageNumber = 0;
        while (matcher.find()) {
            pageNumber = Integer.parseInt(matcher.group(1));
        }
        return pageNumber;
    }
}
