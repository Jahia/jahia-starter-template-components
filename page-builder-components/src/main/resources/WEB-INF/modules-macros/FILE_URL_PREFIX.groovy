import org.slf4j.LoggerFactory

logger = LoggerFactory.getLogger(this.class)
logger.debug("FILE_URL_PREFIX start!")

print "$url.context/files/$renderContext.workspace/sites/$renderContext.site.siteKey/files";

