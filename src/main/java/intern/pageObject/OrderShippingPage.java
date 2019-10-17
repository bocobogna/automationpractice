package intern.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static intern.utils.DataResources.headerLocator;

public class OrderShippingPage implements AtCheck {

    private SelenideElement header = $(headerLocator);
    private SelenideElement termsOfServiceCheckBox = $("#cgv");
    private SelenideElement proceedButton = $("*[class^='button btn btn-default'][class$='button-medium']");

    @Override
    public OrderShippingPage isAt(String headerTxt) {
        header
                .shouldBe(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public OrderShippingPage acceptTermsOfService(){
        termsOfServiceCheckBox
                .should(exist)
                .click();
        SelenideElement checkBox = $x("//div[@id='uniform-cgv']//span");
        checkBox
                .shouldBe(visible)
                .shouldHave(cssClass("checked"));
        return this;
    }

    public OrderShippingPage continueBuyingProcessVerification(){
        proceedButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
