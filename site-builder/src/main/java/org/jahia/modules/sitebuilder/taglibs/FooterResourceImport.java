package org.jahia.modules.sitebuilder.taglibs;

import org.jahia.services.content.JCRNodeWrapper;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

public class FooterResourceImport extends AbstractResourceImport {

    protected String generateImportTags() throws RepositoryException {
        List<String> jsPaths = new ArrayList<>();

        // Add global paths
        jsPaths.add(String.format("/sites/%s/files/assets/global/jsFooter", siteKey));

        if (ctx.isPreviewMode() || ctx.isLiveMode()) {
            jsPaths.add(String.format("/sites/%s/files/assets/previewAndLive/jsFooter", siteKey));
        }

        List<JCRNodeWrapper> jsFiles = getFileNodes(jsPaths);
        StringBuilder sb = new StringBuilder();
        jsFiles.forEach(node -> sb.append(String.format("<script type = \"text/javascript\" src = \"%s\" ></script>%n", node.getUrl())));

        return sb.toString();
    }
}
