package intern.pageObject.fragments;

import com.codeborne.selenide.SelenideElement;
import intern.pageObject.AtCheck;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.*;

public class DialogBox implements AtCheck {

    private SelenideElement dialogBoxHeader1 = $x("//div[@class='layer_cart_product col-xs-12 col-md-6']//h2[1]");
    private SelenideElement dialogBoxHeader2 = $x("//div[@class='layer_cart_cart col-xs-12 col-md-6']//span[2]");
    private SelenideElement cartProductName = $("#layer_cart_product_title");
    private SelenideElement cartProductAttributes = $("#layer_cart_product_attributes"); //Both color and size
    private SelenideElement cartProductPrice = $("#layer_cart_product_price");
    private SelenideElement cartItemsAndShipmentPrice = $("div.layer_cart_row span.ajax_block_cart_total");
    private SelenideElement shipmentCost = $("div.layer_cart_row span.ajax_cart_shipping_cost");
    private SelenideElement cartProductQuantity = $("#layer_cart_product_quantity");
    private SelenideElement proceedToCheckoutButton = $x("//span[contains(text(),'Proceed to checkout')]");

    @Override
    public DialogBox isAt(String headerTxt) {
        dialogBoxHeader1
                .shouldBe(visible)
                .should(matchText(headerTxt));
        return this;
    }

    public DialogBox checkCartProductName(String productName) {
        assertThat(cartProductName.text().contentEquals(productName))
                .as("Product in Cart - Name")
                .isTrue();
        return this;
    }

    public DialogBox checkCartProductAttributes(String productColor, String productSize){
        assertThat(cartProductAttributes.text().contentEquals(productColor + ", " + productSize))
                .as("Product in Cart - Attributes (Color, Size)")
                .isTrue();
        return  this;
    }

    public DialogBox checkCartProductQuantity(Integer productQuantity){
        assertThat(cartProductQuantity.text().contentEquals(String.valueOf(productQuantity)))
                .as("Product in Cart - Quantity")
                .isTrue();
        return this;
    }

    public Float getShipmentCostValue(){
        return Float.valueOf(shipmentCost
                .shouldBe(visible)
                .getText()
                .substring(1));
    }

    public DialogBox checkCartProductPrice(Float productPrice, Integer productQuantity, Float shipmentCost){
        assertThat(cartProductPrice.text().contentEquals("$" + (productPrice * productQuantity)))
                .as("Product in Cart - Price")
                .isTrue();
        assertThat(cartItemsAndShipmentPrice.text().contentEquals("$" + ((productPrice * productQuantity) + shipmentCost)))
                .as("The total amount of your order in Cart - Order Summary")
                .isTrue();
        return this;
    }

    public DialogBox goToCartSummary(){
        proceedToCheckoutButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
