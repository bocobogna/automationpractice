package intern.tests;

import org.junit.jupiter.api.Test;

import static intern.enums.MyAccountOption.ORDER_HISTORY;
import static intern.utils.DataResources.*;
import static intern.utils.DataResources.cartHeaderText;

public class BuyProductTest extends BaseTest {

    @Test
    public void BuyProduct() {
        loginUser();

        pages.navigationHeader
                .goToDressesPage();

        String productCategoryName = dressesHeaderText;
        String productSubcategoryName = summerDressesHeaderText;

        pages.allProductsPage
                .isAt(productCategoryName)
                .goToSubcategoryPage(productSubcategoryName);

        pages.productsSubcategoryPage
                .isAt(productSubcategoryName);

        String productName = pages.productsSubcategoryPage.getNameOfFirstProductInSubcategory();

        pages.productsSubcategoryPage
                .goToFirstProductInSubcategory(productName);

        pages.productPage
                .isAt(productName);

        Float productPrice = pages.productPage.getProductPrice();
        String productColor = "Blue";
        String productSize = "S";
        Integer productQuantity = 1;

        pages.productPage
                .setProductQuantity(productQuantity)
                .setProductSize("S")
                .setProductColor(productColor)
                .addProductToCart();

        pages.dialogBox
                .isAt(addToCartDialogBoxHeaderText)
                .checkCartProductName(productName)
                .checkCartProductAttributes(productColor, productSize);

        Float shipmentCost = pages.dialogBox.getShipmentCostValue();

        pages.dialogBox
                .checkCartProductPrice(productPrice, productQuantity, shipmentCost)
                .checkCartProductQuantity(productQuantity)
                .goToCartSummary();

        pages.orderCartSummaryPage
                .isAt(cartHeaderText)
                .checkCartProductName(productName)
                .checkCartProductPrice(productPrice, productQuantity, shipmentCost)
                .checkCartProductQuantity(productQuantity)
                .checkCartProductAttributes(productColor, productSize)
                .continueBuyingProcessVerification();

        pages.orderAddressPage
                .isAt(addressHeaderText)
                .checkIfDeliveryAndInvoiceDataDuplicates()
                .continueBuyingProcessVerification();

        pages.orderShippingPage
                .isAt(shippingHeaderText)
                .acceptTermsOfService()
                .continueBuyingProcessVerification();

        pages.orderPaymentPage
                .isAt(paymentHeaderText)
                .checkCartProductName(productName)
                .checkCartProductPrice(productPrice, productQuantity, shipmentCost)
                .checkCartProductQuantity(productQuantity)
                .checkCartProductAttributes(productColor, productSize)
                .choosePaymentMethod(paymentMethodBankWire);

        pages.orderSummaryPage
                .isAt(orderSummaryHeaderText)
                .checkPaymentHeader(paymentMethodBankWire)
                .checkOrderPrice(productPrice, productQuantity)
                .continueBuyingProcessVerification();

        pages.orderConfirmationPage
                .isAt(orderConfirmationHeaderText);

        String orderReference = pages.orderConfirmationPage.getOrderReference();

        pages.orderConfirmationPage
                .checkOrderTotalPrice(productPrice, productQuantity);

        pages.mainHeader
                .goToMyAccount();

        pages.myAccountPage
                .isAt(myAccountHeaderText)
                .listAction(ORDER_HISTORY);

        pages.orderHistoryAndDetailsPage
                .isAt(orderHistoryHeaderText)
                .goToOrderDetails(orderReference)
                .checkOrderStatus(paymentMethodBankWire)
                .checkOrderPrice(productPrice, productQuantity, shipmentCost)
                .checkCartProductAttributes(productName, productColor, productSize)
                .checkIfDeliveryAndInvoiceDataDuplicates();

        logoutUser();
    }
}
