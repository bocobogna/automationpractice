package intern.pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderHistoryAndDetailsPage implements AtCheck {

    private SelenideElement header = $x("//h1[@class='page-heading bottom-indent']");
    private SelenideElement ordersList = $("#order-list");
    private SelenideElement orderDetails = $("#block-order-detail");
    private SelenideElement orderStatus = $("div.table_block");
    private ElementsCollection deliveryAddress = $$("ul[class*='address item'] li");
    private ElementsCollection invoiceAddress = $$("ul[class*='address alternate'] li");
    private SelenideElement orderedProductAtributes = $("#order-detail-content tbody td[class='bold'] label");
    private SelenideElement orderedTotalProductPrice = $("#order-detail-content tbody td.price:nth-child(5) label");
    private SelenideElement orderedOneProductPrice = $("#order-detail-content tbody td.price:nth-child(4) label");
    private SelenideElement orderedProductPriceAndShippingCost = $("tr.totalprice.item span.price");

    @Override
    public OrderHistoryAndDetailsPage isAt(String headerTxt) {
        header
                .shouldBe(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public OrderHistoryAndDetailsPage goToOrderDetails(String orderReference){
        ordersList
                .shouldBe(visible)
                .$$x("//tr[contains(@class, 'item')]//a")
                .findBy(text(orderReference))
                .click();
        orderDetails
                .shouldBe(visible);
        return this;
    }

    public OrderHistoryAndDetailsPage checkOrderStatus(String paymentMethod){
        String pinkStatus = orderStatus
                .shouldBe(visible)
                .$x("//span[@class='label dark']")
                .getText();
        assertThat(pinkStatus.contentEquals("On backorder"))
                .as("Warehouse status")
                .isTrue();
        String paymentStatus = orderStatus
                .$x("//span[@class='label']")
                .getText();
        assertThat(paymentStatus.contentEquals("Awaiting " + paymentMethod + " payment"))
                .as("Payment status")
                .isTrue();
        return this;
    }

    public OrderHistoryAndDetailsPage checkIfDeliveryAndInvoiceDataDuplicates(){
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

    public OrderHistoryAndDetailsPage checkOrderPrice(Float productPrice, Integer productQuantity, Float shipmentCost){
        assertThat(orderedTotalProductPrice.text().contentEquals("$" + (productPrice * productQuantity)))
                .as("Product in Cart - Price of all Products")
                .isTrue();
        assertThat(orderedOneProductPrice.text().contentEquals("$" + productPrice))
                .as("Product in Cart Summary - Price  of One Product")
                .isTrue();
        assertThat(orderedProductPriceAndShippingCost.text().contentEquals("$" + ((productPrice * productQuantity) + shipmentCost)))
                .as("Product in Cart Summary - The total amount of your order")
                .isTrue();
        return this;
    }

    public OrderHistoryAndDetailsPage checkCartProductAttributes(String productName, String productColor, String productSize){
        assertThat(orderedProductAtributes.text().contains(productName + " - Color : " + productColor + ", Size : " + productSize))
                .as("Ordered product Attributes - Name, Color, Size")
                .isTrue();
        return  this;
    }
}
