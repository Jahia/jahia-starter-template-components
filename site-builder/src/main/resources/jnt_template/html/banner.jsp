<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="r" uri="http://www.jahia.org/sitebuilder/resourceimport" %>

<c:set var="renderContext" value="${requestScope['renderCtx']}"/>
<c:set var="ctxNode" value="${renderContext.mainResource.node}"/>

<c:set var="allPublished" value="${r:areAllAssetsPublished(renderContext)}"/>
<c:set var="hasCustomPageCss" value="${r:hasPageOverrides(ctxNode)}"/>

<c:if test="${!allPublished || hasCustomPageCss}">
    <template:addResources type="css" resources="site-builder.css" />
    <div class="banner ${ allPublished ? "all-published" : "" }">
        <span>&nbsp;</span>
        <c:choose>
            <c:when test="${allPublished}">
                <%-- page has override files --%>
                <fmt:message key="label.bannerText.hasOverride" />
            </c:when>
            <c:when test="${!hasCustomPageCss}">
                <%-- page has unpublished CSS--%>
                <fmt:message key="label.bannerText.unpublished">
                    <fmt:param value="${renderContext.site.siteKey}"/>
                    <fmt:param value="${renderContext.site.language}"/>
                </fmt:message>

            </c:when>
            <c:otherwise>
                <%-- page has both custom CSS and unpublished files --%>
                <fmt:message key="label.bannerText.overrideAndUnpublished">
                    <fmt:param value="${renderContext.site.siteKey}"/>
                    <fmt:param value="${renderContext.site.language}"/>
                </fmt:message>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>