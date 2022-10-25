package com.testdroid.api.model.enums;

import java.util.List;
import java.util.Map;

import static com.testdroid.api.model.enums.APIPaymentMethod.PlanOperation.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 * <p>
 * Mapping is (isServiceIdPresent, isOldAccountServiceIdPresent, isNewAccountServiceIdPresent, isCallByUser)
 */
public enum APIPaymentMethod {

    PAYPAL(emptyMap()),
    BRAINTREE(createBraintreeMapping()),
    STRIPE(createStripeMapping()),
    INVOICE(createInvoiceMapping()),
    PROMOTION(emptyMap()),
    AWS(emptyMap());

    private static Map<List<Boolean>, List<PlanOperation>> createInvoiceMapping() {
        return Map.of(
                asList(TRUE, FALSE, FALSE, TRUE), singletonList(CREATE),
                asList(TRUE, TRUE, FALSE, TRUE), singletonList(CREATE),
                asList(FALSE, TRUE, FALSE, FALSE), singletonList(CANCEL),
                asList(FALSE, FALSE, TRUE, FALSE), singletonList(ACTIVATE),
                asList(TRUE, FALSE, FALSE, FALSE), asList(CREATE, ACTIVATE),
                asList(TRUE, TRUE, FALSE, FALSE), asList(CREATE, ACTIVATE, CANCEL),
                asList(FALSE, TRUE, TRUE, FALSE), asList(ACTIVATE, CANCEL)
        );
    }

    private static Map<List<Boolean>, List<PlanOperation>> createBraintreeMapping() {
        return Map.of(
                asList(TRUE, FALSE, FALSE, TRUE), asList(CREATE, ACTIVATE),
                asList(FALSE, TRUE, FALSE, TRUE), singletonList(CANCEL),
                asList(FALSE, TRUE, FALSE, FALSE), singletonList(CANCEL),
                asList(TRUE, TRUE, FALSE, TRUE), asList(CREATE, ACTIVATE, CANCEL)
        );
    }

    private static Map<List<Boolean>, List<PlanOperation>> createStripeMapping() {
        return Map.of(
                asList(TRUE, FALSE, FALSE, TRUE), asList(CREATE, ACTIVATE),
                asList(FALSE, TRUE, FALSE, TRUE), singletonList(CANCEL),
                asList(FALSE, TRUE, FALSE, FALSE), singletonList(CANCEL),
                asList(TRUE, TRUE, FALSE, TRUE), asList(CREATE, ACTIVATE, CANCEL)
        );
    }

    private final Map<List<Boolean>, List<PlanOperation>> operationSchema;

    APIPaymentMethod(Map<List<Boolean>, List<PlanOperation>> operationSchema) {
        this.operationSchema = operationSchema;
    }

    public boolean canChange(boolean byUser) {
        return getOperationSchema().containsKey(asList(TRUE, TRUE, FALSE, byUser))
                || getOperationSchema().containsKey(asList(FALSE, TRUE, TRUE, byUser));
    }

    public Map<List<Boolean>, List<PlanOperation>> getOperationSchema() {
        return operationSchema;
    }

    public enum PlanOperation {
        CANCEL,
        CREATE,
        ACTIVATE
    }
}
