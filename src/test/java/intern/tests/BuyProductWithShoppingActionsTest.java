package intern.tests;

import org.junit.jupiter.api.Test;

import static intern.enums.MyAccountOption.ORDER_HISTORY;
import static intern.utils.DataResources.*;

public class BuyProductWithShoppingActionsTest extends BaseTest {

    @Test
    public void buyProductWithShoppingActions(){
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
        String productColor = "Black";
        String productSize = "S";
        Integer productQuantity = 1;

        pages.productPage
                .setProductQuantity(productQuantity)
                .setProductSize("S")
                .setProductColor(productColor)
                .addProductToCart();

        pages.dialogBox
                .isAt(addToCartDialogBoxHeaderText);

        Float shipmentCost = pages.dialogBox.getShipmentCostValue();

        pages.dialogBox
                .checkCartProductName(productName)
                .checkCartProductAttributes(productColor, productSize)
                .checkCartProductPrice(productPrice, productQuantity, shipmentCost)
                .checkCartProductQuantity(productQuantity)
                .goToCartSummary();

        pages.shoppingActions
                .isAt(cartHeaderText)
                .checkCartProductName(productName)
                .checkCartProductPrice(productPrice, productQuantity, shipmentCost)
                .checkCartProductQuantity(productQuantity)
                .checkCartProductAttributes(productColor, productSize)
                .continueBuyingProcessVerification()
                .isAt(addressHeaderText)
                .checkIfDeliveryAndInvoiceDataDuplicates()
                .continueBuyingProcessVerification()
                .isAt(shippingHeaderText)
                .acceptTermsOfService()
                .continueBuyingProcessVerification()
                .isAt(paymentHeaderText)
                .checkCartProductName(productName)
                .checkCartProductPrice(productPrice, productQuantity, shipmentCost)
                .checkCartProductQuantity(productQuantity)
                .checkCartProductAttributes(productColor, productSize)
                .choosePaymentMethod(paymentMethodBankWire)
                .isAt(orderSummaryHeaderText)
                .checkPaymentHeader(paymentMethodBankWire)
                .continueBuyingProcessVerification()
                .isAt(orderConfirmationHeaderText);

        String orderReference = pages.orderConfirmationPage.getOrderReference();

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
