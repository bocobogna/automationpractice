package intern.pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static intern.utils.DataResources.cartHeaderText;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingActions implements AtCheck{

    private SelenideElement header = $("h1.page-heading");
    private SelenideElement cartProductName = $("td.cart_description p.product-name a");
    private SelenideElement cartProductAttributes = $("td.cart_description small a");
    private SelenideElement cartProductQuantity = $("*[class*='cart_quantity text-center']");
    private SelenideElement cartTotalProductPrice = $("#total_product");
    private SelenideElement cartProductPriceAndShippingCost = $("#total_price");
    private SelenideElement cartOneProductPrice = $("span.price.special-price");
    private SelenideElement proceedButton = $("*[class^='button btn btn-default'][class$='button-medium']");

    private ElementsCollection deliveryAddress = $$("ul[class*='address item'] li");
    private ElementsCollection invoiceAddress = $$("ul[class*='address alternate'] li");

    private SelenideElement termsOfServiceCheckBox = $("#cgv");

    private SelenideElement bankWire = $("a.bankwire");
    private SelenideElement paymentMethodBox = $("#HOOK_PAYMENT");

    private SelenideElement paymentHeader = $("h3.page-subheading");
    private SelenideElement allOrderedItemsPriceAndShipping = $("#amount");

    private SelenideElement orderReference = $("div.box");

    @Override
    public ShoppingActions isAt(String headerTxt) {
        header
                .shouldBe(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public ShoppingActions checkCartProductName(String productName) {
        assertThat(cartProductName.text().contentEquals(productName))
                .as("Product in Cart Summary - Name")
                .isTrue();
        return this;
    }

    public ShoppingActions checkCartProductQuantity(Integer productQuantity){
        cartProductQuantity
                .shouldBe(visible);
        if (header.getText().trim().contains(cartHeaderText.toUpperCase())) {
            SelenideElement cartProductCartSummaryQuantity = cartProductQuantity
                    .$("input[class]");
            assertThat(cartProductCartSummaryQuantity.getValue().contains(String.valueOf(productQuantity)))
                    .as("Product in Cart Summary - Quantity")
                    .isTrue();
        } else {
            assertThat(cartProductQuantity.getText().contains(String.valueOf(productQuantity)))
                    .as("Product in Cart Summary - Quantity")
                    .isTrue();
        }
        return this;
    }

    public ShoppingActions checkCartProductPrice(Float productPrice, Integer productQuantity, Float shipmentCost){
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

    public ShoppingActions checkCartProductAttributes(String productColor, String productSize){
        assertThat(cartProductAttributes.text().contains("Color : " + productColor))
                .as("Product in Cart - Attributes (Color")
                .isTrue();
        assertThat(cartProductAttributes.text().contains("Size : " + productSize))
                .as("Product in Cart - Attributes (Size)")
                .isTrue();
        return this;
    }

    public ShoppingActions continueBuyingProcessVerification(){
        proceedButton
                .shouldBe(visible)
                .click();
        return this;
    }

    public ShoppingActions checkIfDeliveryAndInvoiceDataDuplicates() {
        String deliveryData = "";
        String invoiceData = "";

        Pattern pattern = Pattern.compile("([A-Z][a-z]+(,|\\s)|[0-9]+(.))");
        Matcher deliveryMatcher = pattern.matcher(deliveryAddress.texts().toString());
        while (deliveryMatcher.find()) {
            deliveryData = deliveryData + deliveryMatcher.group(0);
        }

        Matcher invoiceMatcher = pattern.matcher(invoiceAddress.texts().toString());
        while (invoiceMatcher.find()) {
            invoiceData = invoiceData + invoiceMatcher.group(0);
        }
        assertThat(deliveryData.contentEquals(invoiceData))
                .as("Delivery and Invoice data are the same")
                .isTrue();
        return this;
    }

    public ShoppingActions acceptTermsOfService(){
        termsOfServiceCheckBox
                .should(exist)
                .click();
        SelenideElement checkBox = $x("//div[@id='uniform-cgv']//span");
        checkBox
                .shouldBe(visible)
                .shouldHave(cssClass("checked"));
        return this;
    }

    public ShoppingActions choosePaymentMethod(String paymentMethod){
        this.paymentMethodBox
                .shouldBe(visible)
                .$$("a")
                .findBy(matchText(paymentMethod.substring(0,2)))
                .click();
        return this;
    }

    public ShoppingActions checkPaymentHeader(String paymentMethod){
        paymentHeader
                .shouldBe(visible)
                .should(matchText(paymentMethod.substring(0,2).toUpperCase()));
        return this;
    }

    public String getOrderReference(){
        String orderRef = "";
        Pattern pattern = Pattern.compile("[A-Z]{5,}");
        Matcher matcher = pattern.matcher(orderReference.getText());
        while (matcher.find()){
            orderRef = matcher.group();
        }
        return orderRef;
    }
}
