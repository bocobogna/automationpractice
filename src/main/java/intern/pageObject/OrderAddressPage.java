package intern.pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static intern.utils.DataResources.headerLocator;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderAddressPage implements AtCheck {

    private SelenideElement header = $(headerLocator);
    private ElementsCollection deliveryAddress = $$("ul[class*='address item'] li");
    private ElementsCollection invoiceAddress = $$("ul[class*='address alternate'] li");
    private SelenideElement proceedButton = $("*[class^='button btn btn-default'][class$='button-medium']");


    @Override
    public OrderAddressPage isAt(String headerTxt) {
        header
                .shouldBe(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public OrderAddressPage checkIfDeliveryAndInvoiceDataDuplicates(){
        String deliveryData = "";
        String invoiceData = "";

        Pattern pattern = Pattern.compile("([A-Z][a-z]+(,|\\s)|[0-9]+(.))");
        Matcher deliveryMatcher = pattern.matcher(deliveryAddress.texts().toString());
        while (deliveryMatcher.find()){
            deliveryData = deliveryData + deliveryMatcher.group(0);
        }

        Matcher invoiceMatcher = pattern.matcher(invoiceAddress.texts().toString());
        while (invoiceMatcher.find()){
            invoiceData = invoiceData + invoiceMatcher.group(0);
        }
        assertThat(deliveryData.contentEquals(invoiceData))
                .as("Delivery and Invoice data are the same")
                .isTrue();
        return this;
    }

    public OrderAddressPage continueBuyingProcessVerification(){
        proceedButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
