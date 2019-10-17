package intern.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static intern.utils.DataResources.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderCartSummaryPage implements AtCheck {

    private SelenideElement header = $(headerLocator);
    private SelenideElement cartProductName = $("td.cart_description p.product-name a");
    private SelenideElement cartProductAttributes = $("td.cart_description small a");
    private SelenideElement cartProductQuantity = $("input.cart_quantity_input.form-control.grey");
    private SelenideElement cartTotalProductPrice = $("#total_product");
    private SelenideElement cartProductPriceAndShippingCost = $("#total_price");
    private SelenideElement cartOneProductPrice = $("span.price.special-price");
    private SelenideElement proceedButton = $("*[class^='button btn btn-default'][class$='button-medium']");

    @Override
    public OrderCartSummaryPage isAt(String headerTxt) {
        header
                .shouldBe(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public OrderCartSummaryPage checkCartProductName(String productName) {
        assertThat(cartProductName.text().contentEquals(productName))
                .as("Product in Cart Summary - Name")
                .isTrue();
        return this;
    }

    public OrderCartSummaryPage checkCartProductQuantity(Integer productQuantity){
        assertThat(cartProductQuantity.getValue().contains(String.valueOf(productQuantity)))
                .as("Product in Cart Summary - Quantity")
                .isTrue();
        return this;
    }

    public OrderCartSummaryPage checkCartProductPrice(Float productPrice, Integer productQuantity, Float shipmentCost){
        assertThat(cartTotalProductPrice.text().contentEquals("$" + (productPrice * productQuantity)))
                .as("Product in Cart - Price of all Products")
                .isTrue();
        assertThat(cartOneProductPrice.text().contentEquals("$" + productPrice))
                .as("Product in Cart Summary - Price  of One Product")
                .isTrue();
        assertThat(cartProductPriceAndShippingCost.text().contentEquals("$" + ((productPrice * productQuantity) + shipmentCost)))
                .as("Product in Cart Summary - The total amount of your order")
                .isTrue();
        return this;
    }

    public OrderCartSummaryPage checkCartProductAttributes(String productColor, String productSize){
        assertThat(cartProductAttributes.text().contains("Color : " + productColor))
                .as("Product in Cart - Attributes (Color")
                .isTrue();
        assertThat(cartProductAttributes.text().contains("Size : " + productSize))
                .as("Product in Cart - Attributes (Size)")
                .isTrue();
        return this;
    }

    public OrderCartSummaryPage continueBuyingProcessVerification(){
        proceedButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
