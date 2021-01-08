package org.jahia.modules.pagebuildercomponents.model;

import net.htmlparser.jericho.StartTag;

import java.util.List;

/**
 * Template area abstraction, helps to generate <template:area /> tags or contains raw html rawValue
 */
public class TemplateFragment {
    private StartTag startTag;
    private HtmlElementType type;
    private String rawValue;
    private String path;
    private String areaType;
    private int limit;
    private List types;

    /**
     * Constructor
     */
    public TemplateFragment() {
        areaType = "jnt:contentList";
    }

    public StartTag getStartTag() {
        return startTag;
    }

    public void setStartTag(StartTag startTag) {
        this.startTag = startTag;
    }

    public HtmlElementType getType() {
        return type;
    }

    public void setType(HtmlElementType type) {
        this.type = type;
    }

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String[] getTypes() {
        return types == null ? null : (String[]) types.toArray(new String[0]);
    }

    public void setTypes(List types) {
        this.types = types;
    }
}
