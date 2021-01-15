package org.jahia.modules.sitebuilder.taglibs;

import org.jahia.modules.sitebuilder.taglibs.model.Resource;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.render.RenderContext;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Resource tag lib function
 */
public final class HeadResourceFunctions {

    private HeadResourceFunctions() {
    }

    /**
     * Gets css and js resources urls
     *
     * @param ctx render context
     * @return List<Resource>
     * @throws RepositoryException RepositoryException
     */
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

        List<JCRNodeWrapper> cssFiles = TaglibUtils.getFileNodes(cssPaths, ctx);
        List<JCRNodeWrapper> jsFiles = TaglibUtils.getFileNodes(jsPaths, ctx);

        if (ctx.isLiveMode()) {
            cssFiles = TaglibUtils.filterMinFileDuplicates(cssFiles);
            jsFiles = TaglibUtils.filterMinFileDuplicates(jsFiles);
        }

        cssFiles.sort(Comparator.comparing(JCRNodeWrapper::getName));
        jsFiles.sort(Comparator.comparing(JCRNodeWrapper::getName));

        List<Resource> resources = new ArrayList<>();

        cssFiles.forEach(file -> resources.add(new Resource("css", file.getUrl())));
        jsFiles.forEach(file -> resources.add(new Resource("js", file.getUrl())));

        return resources;
    }
}
