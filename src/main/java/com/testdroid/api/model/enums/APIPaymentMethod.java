package com.testdroid.api.model.enums;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
public enum APIPaymentMethod {
    PAYPAL,
    BRAINTREE(TRUE),
    INVOICE(FALSE),
    PROMOTION,
    AWS(FALSE);

    private Boolean allowUpdate;

    APIPaymentMethod() {

    }

    APIPaymentMethod(Boolean allowUpdate) {
        this.allowUpdate = allowUpdate;
    }

    public Boolean getAllowUpdate() {
        return allowUpdate;
    }
}
