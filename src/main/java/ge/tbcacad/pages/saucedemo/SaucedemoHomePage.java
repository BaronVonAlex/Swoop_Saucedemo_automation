package ge.tbcacad.pages.saucedemo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SaucedemoHomePage {
    public ElementsCollection
        inventoryItemDiv = $$(".inventory_item");

    public SelenideElement
        inventoryImage = $("img.inventory_item_img");
}
