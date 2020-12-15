<%@ page import="org.jahia.modules.pagebuildercomponents.taglib.PageBuilderLib" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="org.jahia.modules.pagebuildercomponents.model.HtmlElementType" %>
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
<%--@elvariable id="htmlSlices" type="java.util.List"--%>
<%--@elvariable id="htmlElement" type="org.jahia.modules.pagebuildercomponents.model.HtmlElement"--%>

<c:if test="${not empty param.htmlSrcCode}">
    <c:set var="htmlSrcCode" value="${param.htmlSrcCode}"/>
    <c:set value="${pageBuilderParser:getHtmlElements(htmlSrcCode)}" var="htmlSlices" />
    <c:forEach items="${htmlSlices}" var="htmlElement">
        <c:choose>
            <c:when test="${htmlElement.type eq 'TEMPLATE_AREA'}">
                ${htmlElement.startTag.toString()}
                    <template:area path="${htmlElement.value}" areaAsSubNode="true"/>
                </${htmlElement.startTag.name}>
            </c:when>
            <c:otherwise>
                ${htmlElement.value}
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>