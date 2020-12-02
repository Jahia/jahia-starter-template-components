package org.jahia.modules.sitebuilder.taglibs;

import org.jahia.services.content.JCRNodeIteratorWrapper;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.query.QueryResultWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.taglibs.AbstractJahiaTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.query.Query;
import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResourceImport extends AbstractJahiaTag {
    private static Logger logger = LoggerFactory.getLogger(AbstractResourceImport.class);

    protected String siteKey;
    protected transient JCRSessionWrapper session;
    protected transient RenderContext ctx;

    @Override
    public int doEndTag() throws JspException {
        // Note that render context is not available during construction phase
        ctx = getRenderContext();
        siteKey = ctx.getSite().getSiteKey();

        try {
            session = ctx.getMainResource().getNode().getSession();
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

    protected List<JCRNodeWrapper> getFileNodes(List<String> paths) throws RepositoryException {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from [jnt:file] as file where");
        for (int i = 0; i < paths.size(); i++) {
            String optionalOR = i != 0 ? "or" : "";
            sb.append(String.format(" %s isdescendantnode(file, '%s')", optionalOR, paths.get(i)));
        }

        QueryResultWrapper result = session.getWorkspace().getQueryManager().createQuery(sb.toString(), Query.JCR_SQL2).execute();
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

    protected abstract String generateImportTags() throws RepositoryException;
}
