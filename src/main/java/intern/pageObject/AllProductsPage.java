package intern.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static intern.utils.DataResources.*;

public class AllProductsPage implements AtCheck{

    String subCategoryName;

    private SelenideElement header = $(productsHeaderLocator);
    private SelenideElement subCategoryLink;

    //"Casual Dresses"
    //"Evening Dresses"
    //"Summer Dresses"

    @Override
    public AllProductsPage isAt(String headerTxt) {
        header
                .shouldBe(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public AllProductsPage goToSubcategoryPage (String productSubcategoryName){
        subCategoryLink = $x("//a[@class='subcategory-name'][contains(text(),'" + productSubcategoryName + "')]");

        subCategoryLink
                .shouldBe(visible)
                .should(matchText(productSubcategoryName.toUpperCase()))
                .click();
        return  this;
    }
}
