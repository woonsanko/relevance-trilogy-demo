package org.example.relevance.trilogy.demo.rest;

import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.Parameter;

@FieldGroupList(
        {
            @FieldGroup(
                    titleKey = "Filter",
                    value = {
                            "api.tags"
                            }
                    ),
            @FieldGroup(
                    titleKey = "Sorting",
                    value = {
                            "api.sortFields"
                            }
                    )
            }
        )
public interface EventsDocumentResourceInfo {

    @Parameter(name = "api.tags", defaultValue = "", required = false,
            description = "Tags as a comma-separated string to search.")
    public String getTagsCSV();

    @Parameter(name = "api.sortFields", defaultValue = "", required = false,
            description = "Sort fields information as a comma-separated string in a search result. "
                    + "Each item suggests sorting the result by the field name in ascending order by default. "
                    + "If prefixed by '-', it suggests sorting the result by the field name in descending order.")
    public String getSortFields();

}
