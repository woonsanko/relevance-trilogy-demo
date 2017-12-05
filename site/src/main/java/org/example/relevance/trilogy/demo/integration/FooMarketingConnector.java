/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package org.example.relevance.trilogy.demo.integration;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.request.HstRequestContext;

/**
 * Mocking marketing server connector just as an example.
 */
public class FooMarketingConnector {

    private FooMarketingServer server;

    public FooMarketingServer getServer() {
        return server;
    }

    public void setServer(FooMarketingServer server) {
        this.server = server;
    }

    public FooMarketingAccount getAccount() {
        final String email = getEmailCookieValue();

        if (StringUtils.isNotBlank(email)) {
            return server.findAccountByEmail(email);
        }

        return null;
    }

    private String getEmailCookieValue() {
        final HstRequestContext requestContext = RequestContextProvider.get();

        Cookie[] cookies = requestContext.getServletRequest().getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equalsIgnoreCase(cookie.getName(), "email")) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
