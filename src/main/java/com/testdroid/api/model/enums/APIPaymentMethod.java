package com.testdroid.api.model.enums;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
public enum APIPaymentMethod {
    PAYPAL(false, false, false, false, false, false, false, false),
    BRAINTREE(true, true, true, true, false, false, false, true),
    STRIPE(true, true, true, true, false, false, false, true),
    INVOICE(true, false, true, false, true, true, true, true),
    PROMOTION(false, false, false, false, false, false, false, false),
    AWS(true, true, false, true, false, false, false, true);

    private boolean createByUser, activateByUser, changeByUser, cancelByUser,
            createByAdmin, activateByAdmin, changeByAdmin, cancelByAdmin;

    APIPaymentMethod(
            boolean createByUser, boolean activateByUser, boolean changeByUser, boolean cancelByUser,
            boolean createByAdmin, boolean activateByAdmin, boolean changeByAdmin, boolean cancelByAdmin) {
        this.createByUser = createByUser;
        this.activateByUser = activateByUser;
        this.changeByUser = changeByUser;
        this.cancelByUser = cancelByUser;
        this.createByAdmin = createByAdmin;
        this.activateByAdmin = activateByAdmin;
        this.changeByAdmin = changeByAdmin;
        this.cancelByAdmin = cancelByAdmin;
    }

    public boolean canActivate(boolean byUser) {
        return byUser ? activateByUser : activateByAdmin;
    }

    public boolean canCreate(boolean byUser) {
        return byUser ? createByUser : createByAdmin;
    }

    public boolean canCancel(boolean byUser) {
        return byUser ? cancelByUser : cancelByAdmin;
    }

    public boolean canChange(boolean byUser) {
        return byUser ? changeByUser : changeByAdmin;
    }
}
