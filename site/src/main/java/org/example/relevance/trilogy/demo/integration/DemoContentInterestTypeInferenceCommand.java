/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package org.example.relevance.trilogy.demo.integration;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.cms7.inference.engine.api.command.AbstractInferenceCommand;
import com.onehippo.cms7.inference.engine.api.command.InferenceCommandContext;
import com.onehippo.cms7.inference.engine.api.model.GenericCollectorContextModel;
import com.onehippo.cms7.inference.engine.api.model.GenericRequestContextModel;
import com.onehippo.cms7.inference.engine.api.util.CounterUtils;

public class DemoContentInterestTypeInferenceCommand extends AbstractInferenceCommand {

    private static Logger log = LoggerFactory.getLogger(DemoContentInterestTypeInferenceCommand.class);

    @Override
    protected Object executeInternal(InferenceCommandContext commandContext) {
        // The primary goal data to infer from various inputs.
        String interestType = "unknown";

        final GenericRequestContextModel request = getBuiltin().getRequest();

        // Just as an example, input variables here are the current request URI and/or the 'Referer' http header.
        final String requestURI = request.getRequestURI();
        final String referer = request.getHeader("Referer");

        // If it contains either '/events' or '/news', the goal value is determined accordingly.
        for (String paramName : getBuiltin().getParameterNames()) {
            if (paramName.startsWith("uri.mapping.")) {
                String paramValue = getBuiltin().getParameter(paramName);
                String [] pair = StringUtils.split(paramValue, " :");
                String type = pair[0];
                String uri = pair[1];

                if (requestURI.contains(uri)) {
                    interestType = type;
                    break;
                }
            }
        }

        // If the interestType goal value was determined,
        // and if this is invoked in the relevance collector context,
        // you can store extra goal data values map ($.collectorContext.extraData)
        // and increment the counter for the goal value to store it back to the targeting data.
        if (getBuiltin().hasCollectorContext()) {
            // Get the counter map for each interest type goal value.
            final GenericCollectorContextModel collectorContext = getBuiltin().getCollectorContext();

            // Sets the request level goal value before determining the max counter valued type by counterMap.
            collectorContext.setRequestLevelGoalValue(interestType);
            log.debug("requestLevelGoalValue: {}", interestType);

            Map<Object, Number> counterMap = (Map<Object, Number>) collectorContext.getExtraData().get("counterMap");
            // If the counter map doesn't exist yet, create a new one and put it back to extra data map.
            if (counterMap == null) {
                counterMap = CounterUtils.newMap();
                getBuiltin().getCollectorContext().getExtraData().put("counterMap", counterMap);
            }
            interestType = (String) CounterUtils.incrementAndGetMaxKey(counterMap, interestType);
            log.debug("counterMap: {}", counterMap);

            // Just for demonstration purpose, let's show how you can set a custom persona evaluation score like this.
            // Otherwise, by default, the default scorer will set 1.0 for matched classifications.
            collectorContext.setPersonaEvaluationScore(0.9);
        }

        // For an easier extension/integration, let's see how you can add/read extra attributes.
        FooMarketingConnector fooConnector = (FooMarketingConnector) getBuiltin().getAttribute("fooMarketingConnector");
        FooMarketingAccount account = fooConnector != null ? fooConnector.getAccount() : null;
        if (account != null) {
            log.debug("Account : {}", account);
        }

        // Leave a log and return the primary goal value, interestType, finally.
        log.debug("interestType return: {}", interestType);
        return interestType;
    }

}
