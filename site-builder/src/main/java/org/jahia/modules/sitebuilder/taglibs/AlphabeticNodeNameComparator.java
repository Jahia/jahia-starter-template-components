package org.jahia.modules.sitebuilder.taglibs;

import org.jahia.services.content.JCRNodeWrapper;

import java.io.Serializable;
import java.util.Comparator;

/**
 * JCRNodeWrapper name alphabetic comparator
 */
public class AlphabeticNodeNameComparator implements Comparator<JCRNodeWrapper>, Serializable {

    public static final long serialVersionUID = 4786263212434816314L;

    @Override
    public int compare(JCRNodeWrapper o1, JCRNodeWrapper o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
