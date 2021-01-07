package org.jahia.modules.pagebuildercomponents.model;

import net.htmlparser.jericho.StartTag;

import java.util.List;

/**
 * Template area abstraction, helps to generate <template:area /> tags
 */
public class TemplateArea {
    private StartTag startTag;
    private HtmlElementType type;
    private String value;
    private String path;
    private String areaType;
    private int limit;
    private List types;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public List getTypes() {
        return types;
    }

    public void setTypes(List types) {
        this.types = types;
    }
}
