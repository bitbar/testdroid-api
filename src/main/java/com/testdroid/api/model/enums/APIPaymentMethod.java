package com.testdroid.api.model.enums;

import java.util.Collections;
import java.util.HashMap;
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
 *
 * Mapping is (isServiceIdPresent, isOldAcountServiceIdPresent, isNewServiceIdPresent, isCallByUser)
 */
public enum APIPaymentMethod {

    PAYPAL(emptyMap()),
    BRAINTREE(createBraintreeMapping()),
    STRIPE(createStripeMapping()),
    INVOICE(createInvoiceMapping()),
    PROMOTION(emptyMap()),
    AWS(createAwsMapping());

    private static Map<List<Boolean>, List<PlanOperation>> createInvoiceMapping(){
        Map<List<Boolean>, List<PlanOperation>> result = new HashMap<>();
        result.put(asList(TRUE, FALSE, FALSE, TRUE), singletonList(CREATE));
        result.put(asList(TRUE, TRUE, FALSE, TRUE), singletonList(CREATE));
        result.put(asList(FALSE, TRUE, FALSE, FALSE), singletonList(CANCEL));
        result.put(asList(FALSE, FALSE, TRUE, FALSE), singletonList(ACTIVATE));
        result.put(asList(TRUE, FALSE, FALSE, FALSE), asList(CREATE, ACTIVATE));
        result.put(asList(TRUE, TRUE, FALSE, FALSE), asList(CREATE, ACTIVATE, CANCEL));
        result.put(asList(FALSE, TRUE, TRUE, FALSE), asList(ACTIVATE, CANCEL));
        return Collections.unmodifiableMap(result);
    }

    private static Map<List<Boolean>, List<PlanOperation>> createAwsMapping(){
        Map<List<Boolean>, List<PlanOperation>> result = new HashMap<>();
        result.put(asList(TRUE, FALSE, FALSE, TRUE), asList(CREATE, ACTIVATE));
        result.put(asList(FALSE, TRUE, FALSE, TRUE), singletonList(CANCEL));
        result.put(asList(FALSE, TRUE, FALSE, FALSE), singletonList(CANCEL));
        return Collections.unmodifiableMap(result);
    }

    private static Map<List<Boolean>, List<PlanOperation>> createBraintreeMapping(){
        Map<List<Boolean>, List<PlanOperation>> result = new HashMap<>();
        result.put(asList(TRUE, FALSE, FALSE, TRUE), asList(CREATE, ACTIVATE));
        result.put(asList(FALSE, TRUE, FALSE, TRUE), singletonList(CANCEL));
        result.put(asList(FALSE, TRUE, FALSE, FALSE), singletonList(CANCEL));
        result.put(asList(TRUE, TRUE, FALSE, TRUE), asList(CREATE, ACTIVATE, CANCEL));
        return Collections.unmodifiableMap(result);
    }

    private static Map<List<Boolean>, List<PlanOperation>> createStripeMapping(){
        Map<List<Boolean>, List<PlanOperation>> result = new HashMap<>();
        result.put(asList(TRUE, FALSE, FALSE, TRUE), asList(CREATE, ACTIVATE));
        result.put(asList(FALSE, TRUE, FALSE, TRUE), singletonList(CANCEL));
        result.put(asList(FALSE, TRUE, FALSE, FALSE), singletonList(CANCEL));
        result.put(asList(TRUE, TRUE, FALSE, TRUE), asList(CREATE, ACTIVATE, CANCEL));
        return Collections.unmodifiableMap(result);
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
