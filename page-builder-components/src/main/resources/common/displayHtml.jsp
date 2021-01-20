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
<%--@elvariable id="templateFragment" type="org.jahia.modules.pagebuildercomponents.model.TemplateFragment"--%>

<c:if test="${not empty param.htmlSrcCode}">
    <c:set var="htmlSrcCode" value="${param.htmlSrcCode}"/>
    <c:set value="${pageBuilderParser:getTemplateFragments(htmlSrcCode)}" var="htmlSlices" />
    <c:forEach items="${htmlSlices}" var="templateFragment">
        <c:choose>
            <c:when test="${templateFragment.type eq 'TEMPLATE_AREA'}">
                ${templateFragment.startTag.toString()}
                    <template:area
                            path="${templateFragment.path}"
                            areaType="${templateFragment.areaType}"
                            nodeTypes="${not empty templateFragment.types ? fn:join(templateFragment.types, ' ') : null}"
                            listLimit="${templateFragment.limit > 0 ? templateFragment.limit : -1}"
                            areaAsSubNode="true"
                    />
                </${templateFragment.startTag.name}>
            </c:when>
            <c:when test="${templateFragment.type eq 'TEMPLATE_MODULE'}">
                ${templateFragment.startTag.toString()}
                    <template:module path="${templateFragment.path}" nodeTypes="${templateFragment.areaType}"/>
                </${templateFragment.startTag.name}>
            </c:when>
            <c:otherwise>
                ${templateFragment.rawValue}
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>

<c:if test="${renderContext.editMode && not empty currentNode.properties['jcr:description']}">
  <template:addResources type="css" resources="edit.css" />
    <div class="jst-description hide">
        <svg width="2em" height="2em" fill="currentColor" viewBox="0 0 160 160" xmlns="http://www.w3.org/2000/svg" version="1.0">
           <path d="m80 15c-35.88 0-65 29.12-65 65s29.12 65 65 65 65-29.12 65-65-29.12-65-65-65zm0 10c30.36 0 55 24.64 55 55s-24.64 55-55 55-55-24.64-55-55 24.64-55 55-55z"></path>
           <path d="m57.373 18.231a9.3834 9.1153 0 1 1 -18.767 0 9.3834 9.1153 0 1 1 18.767 0z" transform="matrix(1.1989 0 0 1.2342 21.214 28.75)"></path>
           <path d="m90.665 110.96c-0.069 2.73 1.211 3.5 4.327 3.82l5.008 0.1v5.12h-39.073v-5.12l5.503-0.1c3.291-0.1 4.082-1.38 4.327-3.82v-30.813c0.035-4.879-6.296-4.113-10.757-3.968v-5.074l30.665-1.105"></path>
        </svg>
        <span>${currentNode.properties['jcr:description'].string}</span>
    </div>
</c:if>
