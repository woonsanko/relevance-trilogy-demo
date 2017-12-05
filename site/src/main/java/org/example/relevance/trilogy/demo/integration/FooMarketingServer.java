/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package org.example.relevance.trilogy.demo.integration;

import java.util.Map;

/**
 * Mocking marketing server just as a simple example.
 */
public class FooMarketingServer {

    private Map<String, FooMarketingAccount> accountMap;

    public void setAccountMap(Map<String, FooMarketingAccount> accountMap) {
        this.accountMap = accountMap;
    }

    public FooMarketingAccount findAccountByEmail(String email) {
        return accountMap.get(email);
    }
}
