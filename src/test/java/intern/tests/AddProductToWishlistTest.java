package intern.tests;

import org.junit.jupiter.api.Test;

import static intern.utils.DataResources.*;
import static intern.enums.MyAccountOption.*;

public class AddProductToWishlistTest extends BaseTest {

    @Test
    public void AddDressToWishlist(){
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

        String productColor = "Yellow";
        String productSize = "L";
        Integer productQuantity = 1;

        pages.productPage
                .setProductQuantity(productQuantity)
                .setProductSize(productSize)
                .setProductColor(productColor)
                .addProductToWishlist();

        pages.popUpDialog
                .checkPopUpMessage(addToWishlistMessage)
                .closePopUpMessageDialog();

        pages.mainHeader
                .goToMyAccount();

        pages.myAccountPage
                .isAt(myAccountHeaderText)
                .listAction(MY_WISHLIST);

        pages.myWishlistPage
                .isAt(myWishlistHeaderText)
                .checkWishlistsTable()
                .goToMyWishlist()
                .checkCartProductName(productName)
                .checkCartProductQuantity(productQuantity)
                .checkCartProductAttributes(productColor, productSize)
                .deleteWishList();

        logoutUser();
    }
}
