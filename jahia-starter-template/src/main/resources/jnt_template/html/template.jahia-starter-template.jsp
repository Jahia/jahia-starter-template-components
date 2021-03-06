<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="r" uri="http://www.jahia.org/jahiastartertemplate/resourceimport" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<%--@elvariable id="headResources" type="java.util.List"--%>
<%--@elvariable id="resource" type="org.jahia.modules.jahiastartertemplate.taglibs.model.Resource"--%>


<%-- IMPORTS --%>

<%@ page import="org.jahia.modules.jahiastartertemplate.taglibs.model.FileType" %>
<%@ page import="org.jahia.modules.jahiastartertemplate.taglibs.PropConstants" %>

<% pageContext.setAttribute("CSS", FileType.CSS); %>
<% pageContext.setAttribute("JAVASCRIPT", FileType.JAVASCRIPT); %>
<% pageContext.setAttribute("CSS_OVERRIDE_PROP", PropConstants.CSS_OVERRIDE_PROP); %>
<% pageContext.setAttribute("JS_HEAD_OVERRIDE_PROP", PropConstants.JS_HEAD_OVERRIDE_PROP); %>
<% pageContext.setAttribute("JS_BODY_OVERRIDE_PROP", PropConstants.JS_BODY_OVERRIDE_PROP); %>

<c:set var="ctxNode" value="${renderContext.mainResource.node}"/>

<%-- BEGIN: head resources --%>
<%-- global overrides --%>
<c:forEach var="resource" items="${r:getGlobalResources(renderContext)}">
    <template:addCacheDependency flushOnPathMatchingRegexp="${resource.resourceUrl}"/>
    <c:choose>
        <c:when test="${resource.type eq 'css'}">
            <template:addResources type="css" resources="${resource.resourceUrl}"/>
        </c:when>
        <c:when test="${resource.type eq 'js'}">
            <template:addResources type="javascript" resources="${resource.resourceUrl}"/>
        </c:when>
    </c:choose>
</c:forEach>

<%-- preview overrides --%>
<c:forEach var="resource" items="${r:getPreviewResources(renderContext)}">
    <template:addCacheDependency flushOnPathMatchingRegexp="${resource.resourceUrl}"/>
    <c:choose>
        <c:when test="${resource.type eq 'css'}">
            <template:addResources type="css" resources="${resource.resourceUrl}"/>
        </c:when>
        <c:when test="${resource.type eq 'js'}">
            <template:addResources type="javascript" resources="${resource.resourceUrl}"/>
        </c:when>
    </c:choose>
</c:forEach>

<%-- page css overrides --%>
<c:forEach var="url" items="${r:getPageOverrides(ctxNode, CSS_OVERRIDE_PROP, CSS)}">
    <template:addResources type="css" resources="${url}"/>
</c:forEach>

<%-- page head javascript overrides --%>
<c:forEach var="url" items="${r:getPageOverrides(ctxNode, JS_HEAD_OVERRIDE_PROP, JAVASCRIPT)}">
    <template:addResources type="javascript" resources="${url}"/>
</c:forEach>
<%--END: head resources--%>

<%-- page Composer css overrides --%>
<c:forEach var="resource" items="${r:getPageComposerResources(renderContext)}">
    <template:addCacheDependency flushOnPathMatchingRegexp="${resource.resourceUrl}"/>
    <template:addResources type="css" resources="${resource.resourceUrl}"/>
</c:forEach>

<%--BEGIN: Footer resources (body tag)--%>
<c:set var="footerResources" value="${r:footerResources(renderContext)}"/>
<c:forEach var="resource" items="${footerResources}">
    <template:addCacheDependency flushOnPathMatchingRegexp="${resource.resourceUrl}"/>
    <c:choose>
        <c:when test="${resource.type eq 'js'}">
            <template:addResources type="javascript" resources="${resource.resourceUrl}" targetTag="body"/>
        </c:when>
    </c:choose>
</c:forEach>

<%-- page body javascript overrides --%>
<c:forEach var="url" items="${r:getPageOverrides(ctxNode, JS_BODY_OVERRIDE_PROP, JAVASCRIPT)}">
    <template:addResources type="javascript" resources="${url}" targetTag="body"/>
</c:forEach>
<%--END: Footer resources--%>

<%-- Page override banner --%>
<c:if test="${renderContext.editMode}">
    <c:set var="renderCtx" value="${renderContext}" scope="request"/>
    <jsp:include page="banner.jsp"/>
</c:if>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${fn:escapeXml(ctxNode.displayableName)}</title>

<%--BEGIN: custom scripts--%>
    <%--Site level custom scripts--%>
    <r:customScript/>
    <%-- page custom snippet override --%>
    <r:pageCustomSnippet/>
<%--END: custom scripts--%>
</head>

<body>
    <template:area path="pagecontent"/>
</body>

</html>
