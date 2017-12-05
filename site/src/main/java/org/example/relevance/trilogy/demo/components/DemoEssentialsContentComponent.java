/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package org.example.relevance.trilogy.demo.components;

import org.example.relevance.trilogy.demo.beans.NewsDocument;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.component.HstURL;
import org.onehippo.cms7.essentials.components.EssentialsContentComponent;

import com.onehippo.cms7.genericresource.entitybuilder.GenericResourceEntityBuilder;

public class DemoEssentialsContentComponent extends EssentialsContentComponent {

    @Override
    public void doBeforeRender(final HstRequest request, final HstResponse response) {
        super.doBeforeRender(request, response);

        contributeResourceEntities(request, response);
    }

    private void contributeResourceEntities(final HstRequest request, final HstResponse response) {
        GenericResourceEntityBuilder builder = GenericResourceEntityBuilder.get(request.getRequestContext());

        final HippoBean document = (NewsDocument) request.getRequestContext().getContentBean();

        if (document != null) {
            builder.setResourceEntity("document", document);
        }

        final HstURL componentRenderingURL = response.createComponentRenderingURL();
        builder.setResourceEntity("componentRenderingURL", componentRenderingURL);
    }
}
