package intern.pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static intern.utils.DataResources.productsHeaderLocator;

public class ProductsSubcategoryPage implements AtCheck {

    private SelenideElement header = $(productsHeaderLocator);
    private ElementsCollection productsCollection = $$x("//ul[@class='product_list grid row']//a[@class='product-name']");

    @Override
    public ProductsSubcategoryPage isAt(String headerTxt) {
        header
                .shouldBe(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public String getNameOfFirstProductInSubcategory(){
        return productsCollection
                .first()
                .getText();
    }

    public ProductsSubcategoryPage goToFirstProductInSubcategory(String productName){
        productsCollection
                .findBy(text(productName)).click();
        return this;
    }
}
