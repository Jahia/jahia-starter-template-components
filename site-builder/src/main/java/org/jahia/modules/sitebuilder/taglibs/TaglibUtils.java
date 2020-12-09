package org.jahia.modules.sitebuilder.taglibs;

import org.apache.commons.lang.StringUtils;
import org.jahia.services.content.JCRNodeIteratorWrapper;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.query.QueryResultWrapper;
import org.jahia.services.render.RenderContext;

import javax.jcr.RepositoryException;
import javax.jcr.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper functions
 */
public final class TaglibUtils {

    private static final String MIN_EXTENSION = ".min.";

    private TaglibUtils() {
    }

    /**
     * Gets js and css files under paths
     *
     * @param paths paths
     * @param renderContext render context
     * @return List<JCRNodeWrapper>
     * @throws RepositoryException RepositoryException
     */
    public static List<JCRNodeWrapper> getFileNodes(List<String> paths, RenderContext renderContext) throws RepositoryException {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from [jnt:file] as file where");
        for (int i = 0; i < paths.size(); i++) {
            String optionalOR = i != 0 ? "or" : "";
            sb.append(String.format(" %s isdescendantnode(file, '%s')", optionalOR, paths.get(i)));
        }

        QueryResultWrapper result = renderContext.getMainResource().getNode().getSession().getWorkspace()
                .getQueryManager().createQuery(sb.toString(), Query.JCR_SQL2).execute();
        JCRNodeIteratorWrapper it = result.getNodes();
        List<JCRNodeWrapper> files = new ArrayList<>();
        while (it.hasNext()) {
            JCRNodeWrapper file = (JCRNodeWrapper) it.nextNode();
            if (file.getName().endsWith(".js") || file.getName().endsWith(".css")) {
                files.add(file);
            }
        }

        return files;
    }

    /**
     * Removes duplicate entries for .min files
     *
     * @param list list of nodes
     * @return List<JCRNodeWrapper>
     */
    public static List<JCRNodeWrapper> filterMinFileDuplicates(List<JCRNodeWrapper> list) {
        List<JCRNodeWrapper> minList = list.stream()
                .filter(file -> file.getName().contains(MIN_EXTENSION))
                .collect(Collectors.toList());

        if (minList.isEmpty()) {
            return list;
        }

        List<String> minNames = minList.stream()
                .map(file -> StringUtils.substringBefore(file.getName(), MIN_EXTENSION))
                .collect(Collectors.toList());

        list.removeAll(minList);
        List<JCRNodeWrapper> allNotMin = list.stream()
                .filter(file -> !minNames.contains(StringUtils.substringBeforeLast(file.getName(), ".")))
                .collect(Collectors.toList());

        minList.addAll(allNotMin);
        return minList;
    }
}
