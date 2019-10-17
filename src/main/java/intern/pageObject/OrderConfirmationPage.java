package intern.pageObject;

import com.codeborne.selenide.SelenideElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static intern.utils.DataResources.headerLocator;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderConfirmationPage implements AtCheck {

    private SelenideElement header = $(headerLocator);
    private SelenideElement orderTotalPrice = $("span.price strong");
    private SelenideElement backToOrdersButton = $("a.button-exclusive.btn.btn-default");
    private SelenideElement orderReference = $("div.box");

    @Override
    public OrderConfirmationPage isAt(String headerTxt) {
        header
                .shouldBe(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public OrderConfirmationPage checkOrderTotalPrice(Float productPrice, Integer productQuantity){
        assertThat(orderTotalPrice.text().contentEquals("$" + ((productPrice * productQuantity) + 2)))
                .as("The total amount of your order - Order Summary")
                .isTrue();
        return this;
    }

    public String getOrderReference(){
        orderReference
                .shouldBe(visible);
        String orderRef = "";
        Pattern pattern = Pattern.compile("[A-Z]{5,}");
        Matcher matcher = pattern.matcher(orderReference.getText());
        while (matcher.find()){
            orderRef = matcher.group();
        }
        return orderRef;
    }

    public OrderConfirmationPage goToOrdersPage(){
        backToOrdersButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
