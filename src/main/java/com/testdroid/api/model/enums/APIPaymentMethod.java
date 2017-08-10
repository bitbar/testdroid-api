package com.testdroid.api.model.enums;

import static java.lang.Boolean.*;
/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
public enum APIPaymentMethod {
    PAYPAL,
    BRAINTREE(TRUE),
    INVOICE(FALSE),
    PROMOTION,
    BLUEMIX(TRUE),
    AWS(FALSE);

    private Boolean allowUpdate;

    private APIPaymentMethod(){

    }

    private APIPaymentMethod(Boolean allowUpdate){
        this.allowUpdate = allowUpdate;
    }

    public Boolean getAllowUpdate() {
        return allowUpdate;
    }
}
