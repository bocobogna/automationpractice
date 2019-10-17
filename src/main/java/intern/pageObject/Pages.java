package intern.pageObject;

import intern.pageObject.fragments.DialogBox;
import intern.pageObject.fragments.MainHeader;
import intern.pageObject.fragments.NavigationHeader;
import intern.pageObject.fragments.PopUpDialog;

public class Pages {
    public MainPage mainPage = new MainPage();
    public LoginPage loginPage = new LoginPage();
    public MyAccountPage myAccountPage = new MyAccountPage();
    public AllProductsPage allProductsPage = new AllProductsPage();
    public ProductsSubcategoryPage productsSubcategoryPage = new ProductsSubcategoryPage();
    public ProductPage productPage = new ProductPage();
    public OrderCartSummaryPage orderCartSummaryPage = new OrderCartSummaryPage();
    public MyWishlistPage myWishlistPage = new MyWishlistPage();
    public OrderAddressPage orderAddressPage = new OrderAddressPage();
    public OrderShippingPage orderShippingPage = new OrderShippingPage();
    public OrderPaymentPage orderPaymentPage = new OrderPaymentPage();
    public OrderSummaryPage orderSummaryPage = new OrderSummaryPage();
    public OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage();
    public OrderHistoryAndDetailsPage orderHistoryAndDetailsPage = new OrderHistoryAndDetailsPage();
    public ShoppingActions shoppingActions = new ShoppingActions();

    public MainHeader mainHeader = new MainHeader();
    public NavigationHeader navigationHeader = new NavigationHeader();
    public DialogBox dialogBox = new DialogBox();
    public PopUpDialog popUpDialog = new PopUpDialog();
}
