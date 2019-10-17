package intern.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static intern.utils.DataResources.headerLocator;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderSummaryPage implements AtCheck {

    private SelenideElement header = $(headerLocator);
    private SelenideElement paymentHeader = $("h3.page-subheading");
    private SelenideElement allOrderedItemsPriceAndShipping = $("#amount");
    private SelenideElement proceedButton = $("*[class^='button btn btn-default'][class$='button-medium']");

    @Override
    public OrderSummaryPage isAt(String headerTxt) {
        header
                .shouldBe(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public OrderSummaryPage checkPaymentHeader(String paymentMethod){
        paymentHeader
                .shouldBe(visible)
                .should(matchText(paymentMethod.toUpperCase().substring(0,2)));
        return this;
    }

    public OrderSummaryPage checkOrderPrice(Float productPrice, Integer productQuantity){
        assertThat(allOrderedItemsPriceAndShipping.text().contentEquals("$" + ((productPrice * productQuantity) + 2)))
                .as("The total amount of your order - Order Summary")
                .isTrue();
        return this;
    }

    public OrderSummaryPage continueBuyingProcessVerification(){
        proceedButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
