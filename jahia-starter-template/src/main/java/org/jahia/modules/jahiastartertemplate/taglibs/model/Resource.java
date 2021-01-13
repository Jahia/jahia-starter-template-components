package org.jahia.modules.jahiastartertemplate.taglibs.model;

/**
 * Resource abstraction
 */
public class Resource {
    private String type;
    private String resourceUrl;

    /**
     * Constructor
     *
     * @param type css or js
     * @param resourceUrl String
     */
    public Resource(String type, String resourceUrl) {
        this.type = type;
        this.resourceUrl = resourceUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
}
