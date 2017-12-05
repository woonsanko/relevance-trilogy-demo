package org.example.relevance.trilogy.demo.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.example.relevance.trilogy.demo.beans.EventsDocument;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.hippoecm.hst.core.parameters.ParametersInfoProvider;
import org.onehippo.cms7.essentials.components.paging.DefaultPagination;
import org.onehippo.cms7.essentials.components.paging.IterablePagination;
import org.onehippo.cms7.essentials.components.paging.Pageable;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;
import org.onehippo.cms7.essentials.components.rest.ctx.DefaultRestContext;
import org.onehippo.cms7.essentials.components.rest.ctx.RestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @version "$Id$"
 */

@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("/EventsDocument/")
@Api(value = "EventsDocument")
@ParametersInfo(type=EventsDocumentResourceInfo.class)
public class EventsDocumentResource extends BaseRestResource {

    private static Logger log = LoggerFactory.getLogger(EventsDocumentResource.class);

    @GET
    @Path("/")
    @ApiOperation(value = "Finds all events content pages", response = Pageable.class)
    public Pageable<EventsDocument> index(
            @Context HttpServletRequest request,
            @Context ParametersInfoProvider paramsInfoProvider) {
        final EventsDocumentResourceInfo paramsInfo = paramsInfoProvider.getParametersInfo();

        try {
            final RestContext context = new DefaultRestContext(this, request);
            final HstQuery hstQuery = createQuery(context, EventsDocument.class, Subtypes.INCLUDE);

            String [] tags = StringUtils.split(paramsInfo.getTagsCSV(), "\t\r\n, ");

            if (tags != null && tags.length > 0) {
                Filter filter = hstQuery.createFilter();
                for (String tag : tags) {
                    Filter orFilter = hstQuery.createFilter();
                    orFilter.addContains("relevancetrilogydemo:tags", tag);
                    filter.addOrFilter(orFilter);
                }
                hstQuery.setFilter(filter);
            }

            String [] sortFields = StringUtils.split(paramsInfo.getSortFields(), "\t\r\n, ");

            if (sortFields != null) {
                for (String sortField : sortFields) {
                    if (sortField.startsWith("-")) {
                        hstQuery.addOrderByDescending(sortField.substring(1));
                    } else {
                        hstQuery.addOrderByAscending(sortField);
                    }
                }
            }

            final HstQueryResult execute = hstQuery.execute();
            return new IterablePagination<>(
                    execute.getHippoBeans(),
                    execute.getTotalSize(),
                    context.getPageSize(),
                    context.getPage());
        } catch (QueryException e) {
            log.error("Error finding beans", e);
        }

        return DefaultPagination.emptyCollection();
    }

    @GET
    @Path("/page/{page}")
    @ApiOperation(value = "Finds a specific events content page", response = Pageable.class)
    public Pageable<EventsDocument> page(
            @Context HttpServletRequest request,
            @Context ParametersInfoProvider paramsInfoProvider,
            @PathParam("page") int page) {
        final EventsDocumentResourceInfo paramsInfo = paramsInfoProvider.getParametersInfo();
        return findBeans(new DefaultRestContext(this, request, page, DefaultRestContext.PAGE_SIZE), EventsDocument.class);
    }

    @GET
    @Path("/page/{page}/{pageSize}")
    @ApiOperation(value = "Finds a specific events content page with specifying the page size", response = Pageable.class)
    public Pageable<EventsDocument> pageForSize(
            @Context HttpServletRequest request,
            @Context ParametersInfoProvider paramsInfoProvider,
            @PathParam("page") int page, @PathParam("pageSize") int pageSize) {
        final EventsDocumentResourceInfo paramsInfo = paramsInfoProvider.getParametersInfo();
        return findBeans(new DefaultRestContext(this, request, page, pageSize), EventsDocument.class);
    }

}
