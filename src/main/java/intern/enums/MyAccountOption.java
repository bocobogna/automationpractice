package intern.enums;

public enum MyAccountOption {
    ORDER_HISTORY("Order history and details"),
    MY_WISHLIST("My wishlists");

    private String option;

    MyAccountOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
