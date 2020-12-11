<%@ page import="org.jahia.modules.pagebuildercomponents.taglib.PageBuilderLib" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="ui" uri="http://www.jahia.org/tags/uiComponentsLib" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="query" uri="http://www.jahia.org/tags/queryLib" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="s" uri="http://www.jahia.org/tags/search" %>
<%@ taglib prefix="pageBuilderParser" uri="http://www.jahia.org/tags/pagebuilderparser" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<c:set value="${pageBuilderParser:templatePlaceholder()}" var="templateplaceholder" />
<c:if test="${not empty currentNode.properties.htmlSourceFile.node.path}" >
    <c:set value="${jcr:getChildrenOfType(currentNode.properties.htmlSourceFile.node, 'jnt:resource')[0].properties['jcr:data']}"
           var="htmlSource" />
</c:if>
<c:set value="${pageBuilderParser:getHtmlSlices(htmlSource)}" var="htmlSlices" />
<c:forEach items="${htmlSlices}" var="htmlSlice">
    <c:choose>
        <c:when test="${fn:containsIgnoreCase(htmlSlice, templateplaceholder)}">
            <template:area path="${pageBuilderParser:getAreaId(htmlSlice)}"/>
        </c:when>
        <c:otherwise>
            ${htmlSlice}
        </c:otherwise>
    </c:choose>
</c:forEach>

