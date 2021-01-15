package org.jahia.modules.sitebuilder.taglibs;

import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.taglibs.AbstractJahiaTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads resources into footer
 */
public class FooterResourceImport extends AbstractJahiaTag {

    private static Logger logger = LoggerFactory.getLogger(FooterResourceImport.class);
    private static final long serialVersionUID = -588054644278368495L;

    @Override
    public int doEndTag() throws JspException {
        try {
            writeToContext(generateImportTags());
        } catch (IOException e) {
            logger.error("Failed to load resource for site: {}", e.getMessage());
        } catch (RepositoryException e) {
            logger.error("Failed to read jcr while loading resources: {}", e.getMessage());
        }
        return super.doEndTag();
    }

    private void writeToContext(String data) throws IOException {
        pageContext.getOut().print(data);
    }

    private String generateImportTags() throws RepositoryException {
        RenderContext ctx = getRenderContext();
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

        jsFiles.sort(new AlphabeticNodeNameComparator());

        StringBuilder sb = new StringBuilder();
        jsFiles.forEach(node -> sb.append(String.format("<script type = \"text/javascript\" src = \"%s\" ></script>%n", node.getUrl())));

        return sb.toString();
    }
}
