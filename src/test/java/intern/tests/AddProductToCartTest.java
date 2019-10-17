package intern.tests;

import org.junit.jupiter.api.Test;

import static intern.utils.DataResources.*;

public class AddProductToCartTest extends BaseTest{

    @Test
    public void AddDressToCart() {
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
        String productSize = "M";
        Integer productQuantity = 2;

        pages.productPage
                .setProductQuantity(productQuantity)
                .setProductSize(productSize)
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

        pages.orderCartSummaryPage
                .isAt(cartHeaderText)
                .checkCartProductName(productName)
                .checkCartProductPrice(productPrice, productQuantity, shipmentCost)
                .checkCartProductQuantity(productQuantity)
                .checkCartProductAttributes(productColor, productSize);

        logoutUser();
    }
}
