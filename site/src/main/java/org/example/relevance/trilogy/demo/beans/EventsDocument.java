package org.example.relevance.trilogy.demo.beans;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoHtmlAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoGalleryImageAdapter;

@XmlRootElement(name = "eventsdocument")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "relevancetrilogydemo:eventsdocument")
@Node(jcrType = "relevancetrilogydemo:eventsdocument")
public class EventsDocument extends HippoDocument {
    /** 
     * The document type of the events document.
     */
    public final static String DOCUMENT_TYPE = "relevancetrilogydemo:eventsdocument";
    private final static String TITLE = "relevancetrilogydemo:title";
    private final static String DATE = "relevancetrilogydemo:date";
    private final static String INTRODUCTION = "relevancetrilogydemo:introduction";
    private final static String IMAGE = "relevancetrilogydemo:image";
    private final static String CONTENT = "relevancetrilogydemo:content";
    private final static String LOCATION = "relevancetrilogydemo:location";
    private final static String END_DATE = "relevancetrilogydemo:enddate";

    /** 
     * Get the title of the document.
     * @return the title
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "relevancetrilogydemo:title")
    public String getTitle() {
        return getProperty(TITLE);
    }

    /** 
     * Get the date of the document, i.e. the start date of the event.
     * @return the (start) date
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "relevancetrilogydemo:date")
    public Calendar getDate() {
        return getProperty(DATE);
    }

    /** 
     * Get the introduction of the document.
     * @return the introduction
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "relevancetrilogydemo:introduction")
    public String getIntroduction() {
        return getProperty(INTRODUCTION);
    }

    /** 
     * Get the image of the document.
     * @return the image
     */
    @XmlJavaTypeAdapter(HippoGalleryImageAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "relevancetrilogydemo:image")
    public HippoGalleryImageSet getImage() {
        return getLinkedBean(IMAGE, HippoGalleryImageSet.class);
    }

    /** 
     * Get the main content of the document.
     * @return the content
     */
    @XmlJavaTypeAdapter(HippoHtmlAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "relevancetrilogydemo:content")
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    /** 
     * Get the location of the document.
     * @return the location
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "relevancetrilogydemo:location")
    public String getLocation() {
        return getProperty(LOCATION);
    }

    /** 
     * Get the end date of the document, i.e. the end date of the event.
     * @return the end date
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "relevancetrilogydemo:enddate")
    public Calendar getEndDate() {
        return getProperty(END_DATE);
    }

    @XmlElement
    public String getTagsCSV() {
        return StringUtils.join((String []) getProperty("relevancetrilogydemo:tags"), ",");
    }
}
