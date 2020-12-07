package org.jahia.modules.sitebuilder.taglibs.model;

public class Resource {
    private String type;
    private String resourceUrl;

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
