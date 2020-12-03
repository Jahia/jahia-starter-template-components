package org.jahia.modules.sitebuilder.taglibs;

import org.jahia.services.content.JCRNodeWrapper;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads resources into footer
 */
public class FooterResourceImport extends AbstractResourceImport {

    private static final long serialVersionUID = -588054644278368495L;

    protected String generateImportTags() throws RepositoryException {
        List<String> jsPaths = new ArrayList<>();

        // Add global paths
        jsPaths.add(String.format("/sites/%s/files/assets/global/jsFooter", siteKey));

        if (ctx.isPreviewMode() || ctx.isLiveMode()) {
            jsPaths.add(String.format("/sites/%s/files/assets/previewAndLive/jsFooter", siteKey));
        }

        List<JCRNodeWrapper> jsFiles = getFileNodes(jsPaths);

        if (ctx.isLiveMode()) {
            jsFiles = filterMinFileDuplicates(jsFiles);
        }

        StringBuilder sb = new StringBuilder();
        jsFiles.forEach(node -> sb.append(String.format("<script type = \"text/javascript\" src = \"%s\" ></script>%n", node.getUrl())));

        return sb.toString();
    }
}
