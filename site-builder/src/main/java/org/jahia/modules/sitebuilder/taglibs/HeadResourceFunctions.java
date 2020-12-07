package org.jahia.modules.sitebuilder.taglibs;

import org.apache.commons.lang.StringUtils;
import org.jahia.modules.sitebuilder.taglibs.model.Resource;
import org.jahia.services.content.JCRNodeIteratorWrapper;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.query.QueryResultWrapper;
import org.jahia.services.render.RenderContext;

import javax.jcr.RepositoryException;
import javax.jcr.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HeadResourceFunctions {

    protected static final String MIN = ".min.";

    public static List<Resource> getResources(RenderContext ctx) throws RepositoryException {
        String siteKey = ctx.getSite().getSiteKey();
        List<String> cssPaths = new ArrayList<>();
        List<String> jsPaths = new ArrayList<>();

        // Add global paths
        cssPaths.add(String.format("/sites/%s/files/assets/global/css", siteKey));
        jsPaths.add(String.format("/sites/%s/files/assets/global/jsHead", siteKey));

        if (ctx.isEditMode()) {
            cssPaths.add(String.format("/sites/%s/files/assets/pageComposerOnly/css", siteKey));
        }

        if (ctx.isPreviewMode() || ctx.isLiveMode()) {
            cssPaths.add(String.format("/sites/%s/files/assets/previewAndLive/css", siteKey));
            jsPaths.add(String.format("/sites/%s/files/assets/previewAndLive/jsHead", siteKey));
        }

        List<JCRNodeWrapper> cssFiles = getFileNodes(cssPaths, ctx);
        List<JCRNodeWrapper> jsFiles = getFileNodes(jsPaths, ctx);

        if (ctx.isLiveMode()) {
            cssFiles = filterMinFileDuplicates(cssFiles);
            jsFiles = filterMinFileDuplicates(jsFiles);
        }

        List<Resource> resources = new ArrayList<>();

        cssFiles.forEach(file -> resources.add(new Resource("css", file.getUrl())));
        jsFiles.forEach(file -> resources.add(new Resource("js", file.getUrl())));

        return resources;
    }

    private static List<JCRNodeWrapper> getFileNodes(List<String> paths, RenderContext renderContext) throws RepositoryException {
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

    private static List<JCRNodeWrapper> filterMinFileDuplicates(List<JCRNodeWrapper> list) {
        List<JCRNodeWrapper> minList = list.stream()
                .filter(file -> file.getName().contains(MIN))
                .collect(Collectors.toList());

        if (minList.isEmpty()) {
            return list;
        }

        List<String> minNames = minList.stream()
                .map(file -> StringUtils.substringBefore(file.getName(), MIN))
                .collect(Collectors.toList());

        list.removeAll(minList);
        List<JCRNodeWrapper> allNotMin = list.stream()
                .filter(file -> !minNames.contains(StringUtils.substringBeforeLast(file.getName(), ".")))
                .collect(Collectors.toList());

        minList.addAll(allNotMin);
        return minList;
    }
}
