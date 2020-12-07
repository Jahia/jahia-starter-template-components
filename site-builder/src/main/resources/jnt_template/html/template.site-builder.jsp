<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="r" uri="http://www.jahia.org/sitebuilder/resourceimport" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>

<c:set var="headResources" value="${r:headResources(renderContext)}"/>
<c:forEach var="resource" items="${headResources}">
    <c:choose>
        <c:when test="${resource.type eq 'css'}">
            <template:addResources type="css" resources="${resource.resourceUrl}"/>
        </c:when>
        <c:when test="${resource.type eq 'js'}">
            <template:addResources type="javascript" resources="${resource.resourceUrl}"/>
        </c:when>
    </c:choose>
</c:forEach>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title>${fn:escapeXml(renderContext.mainResource.node.displayableName)}</title>
<%--    Import resources--%>
    <r:customScript/>
<%--    <r:headResources/>--%>
</head>
<body>

<!--start bodywrapper-->
<div class="bodywrapper">
    <template:area path="pagecontent"/>
</div>
<!--stop bodywrapper-->

<%--Import resources--%>
<r:footerResources/>
</body>
</html>
