package org.jahia.modules.sitebuilder.taglibs;

import org.jahia.services.content.JCRNodeWrapper;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

public class HeadResourceImport extends AbstractResourceImport {

    protected String generateImportTags() throws RepositoryException {
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

        List<JCRNodeWrapper> cssFiles = getFileNodes(cssPaths);
        List<JCRNodeWrapper> jsFiles = getFileNodes(jsPaths);
        StringBuilder sb = new StringBuilder();
        cssFiles.forEach(node -> sb.append(String.format("<link rel=\"stylesheet\" href=\"%s\">%n", node.getUrl())));
        jsFiles.forEach(node -> sb.append(String.format("<script type = \"text/javascript\" src = \"%s\" ></script>%n", node.getUrl())));

        return sb.toString();
    }

}
