package org.jahia.modules.sitebuilder.taglibs;

import org.jahia.modules.sitebuilder.taglibs.model.Resource;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.render.RenderContext;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Loads resources into footer
 */
public class FooterResourceFunctions {


    private FooterResourceFunctions() {
    }

    /**
     * Gets js resources urls
     *
     * @param ctx render context
     * @return List<Resource>
     * @throws RepositoryException RepositoryException
     */
    public static List<Resource> getResources(RenderContext ctx) throws RepositoryException {
        String siteKey = ctx.getSite().getSiteKey();
        List<String> jsPaths = new ArrayList<>();

        // Add global paths
        jsPaths.add(String.format("/sites/%s/files/assets/global/jsFooter", siteKey));

        if (ctx.isPreviewMode() || ctx.isLiveMode()) {
            jsPaths.add(String.format("/sites/%s/files/assets/previewAndLive/jsFooter", siteKey));
        }

        List<JCRNodeWrapper> jsFiles = TaglibUtils.getFileNodes(jsPaths, ctx);

        if (ctx.isLiveMode()) {
            jsFiles = TaglibUtils.filterMinFileDuplicates(jsFiles);
        }

        jsFiles.sort(Comparator.comparing(JCRNodeWrapper::getName));

        List<Resource> resources = new ArrayList<>();

        jsFiles.forEach(file -> resources.add(new Resource("js", file.getUrl())));

        return resources;
    }
}
