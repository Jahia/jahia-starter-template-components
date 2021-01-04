<%@ page import="org.jahia.services.content.JCRNodeWrapper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="propertyDefinition" type="org.jahia.services.content.nodetypes.ExtendedPropertyDefinition"--%>
<%--@elvariable id="type" type="org.jahia.services.content.nodetypes.ExtendedNodeType"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>

<%--Get node properties and set variables in context--%>
<jcr:nodeProperty name="jcr:title" node="${currentNode}" var="title"/>
<jcr:nodeProperty name="j:baselineNode" node="${currentNode}" var="baseline"/>
<jcr:nodeProperty name="j:maxDepth" node="${currentNode}" var="maxDepth"/>
<jcr:nodeProperty name="j:startLevel" node="${currentNode}" var="startLevel"/>
<jcr:nodeProperty name="j:styleName" node="${currentNode}" var="styleName"/>
<jcr:nodeProperty name="j:layoutID" node="${currentNode}" var="layoutID"/>
<jcr:nodeProperty name="j:menuItemView" node="${currentNode}" var="menuItemTemplate"/>



<%--Get custom navmenu --%>
<jcr:nodeProperty name="ulclass1" node="${currentNode}" var="ulclass1"/>
<jcr:nodeProperty name="liclass1" node="${currentNode}" var="liclass1"/>
<jcr:nodeProperty name="aclass1" node="${currentNode}" var="aclass1"/>
<jcr:nodeProperty name="ulclass2" node="${currentNode}" var="ulclass2"/>
<jcr:nodeProperty name="liclass2" node="${currentNode}" var="liclass2"/>
<jcr:nodeProperty name="aclass2" node="${currentNode}" var="aclass2"/>
<jcr:nodeProperty name="openingtag" node="${currentNode}" var="openingtag"/>
<jcr:nodeProperty name="openintagproperties" node="${currentNode}" var="openintagproperties"/>
<jcr:nodeProperty name="liclasshaschildren" node="${currentNode}" var="liclasshaschildren"/>
<jcr:nodeProperty name="aclasshaschildren" node="${currentNode}" var="aclasshaschildren"/>



<%--template used to display menu items, menuElement is the default--%>

<c:set var="menuItemTemplate" value="menuElement2" />


<%--node to display (get thru parameter if it is a child rendering)--%>

<c:if test="${empty currentResource.moduleParams.base}">
    <c:if test="${empty baseline or baseline.string eq 'home'}">
        <c:set var="current" value="${currentNode.resolveSite.home}"/>
    </c:if>
    <c:if test="${baseline.string eq 'currentPage'}">
        <c:set var="current" value="${jcr:getMeAndParentsOfType(renderContext.mainResource.node, 'jnt:page')[0]}" />
    </c:if>
</c:if>
<c:if test="${not empty currentResource.moduleParams.base}">
    <jcr:node var="current" uuid="${currentResource.moduleParams.base}" />
</c:if>
<template:addCacheDependency path="${renderContext.mainResource.node.parent.path}"/>
<%--initialize startlevel--%>
<c:set var="startLevelValue" value="0"/>
<c:if test="${not empty startLevel}">
    <c:set var="startLevelValue" value="${startLevel.long}"/>
</c:if>

<%--initialize menuLevel--%>
<c:set var="navMenuLevel" value="${empty currentResource.moduleParams.navMenuLevel ? 1 : currentResource.moduleParams.navMenuLevel}"/>

<%--get children to iterate over--%>
<c:if test="${not empty current}">
    <%--this dependency makes the cache works--%>
    <template:addCacheDependency node="${current}"/>
    <c:set var="items" value="${jcr:getChildrenOfType(current,'jmix:navMenuItem')}"/>
</c:if>

<%--render (id and class)--%>
<c:if test="${navMenuLevel == 1}">
    <c:if test="${!empty openingtag}">
        <${openingtag} class="${openintagproperties}">
    </c:if>
</c:if>

<c:choose>
    <c:when test="${navMenuLevel == 1}">
        <ul class="${ulclass1}">
    </c:when>
    <c:otherwise>
        <ul class="${ulclass2}">
    </c:otherwise>
</c:choose>

<c:forEach items="${items}" var="menuItem">
    <%--iterate over each menu item--%>
    <c:set var="inpath" value="${fn:startsWith(renderContext.mainResource.node.path, menuItem.path)}"/>
    <%--test if the item and current menu type matches--%>
    <c:set var="correctType" value="true"/>
    <c:if test="${!empty menuItem.properties['j:displayInMenuName']}">
        <c:set var="correctType" value="false"/>
        <c:forEach items="${menuItem.properties['j:displayInMenuName']}" var="display">
            <c:if test="${display.string eq currentNode.name}">
                <c:set var="correctType" value="${display.string eq currentNode.name}"/>
            </c:if>
        </c:forEach>
    </c:if>
    <c:set var="hasChildren" value="${navMenuLevel < maxDepth.long && jcr:hasChildrenOfType(menuItem,'jnt:page,jnt:nodeLink,jnt:externalLink')}"/>
    <c:choose>
        <c:when test="${startLevelValue < navMenuLevel}">
            <c:if test="${(startLevelValue < navMenuLevel or inpath) and correctType}">
                <c:choose>
                    <c:when test="${navMenuLevel == 1 && hasChildren && !empty liclass1}">
                        <li class="${liclasshaschildren}&nbsp;${liclass1}">
                    </c:when>
                    <c:when test="${navMenuLevel == 1 && hasChildren && empty liclass1}">
                        <li class="${liclasshaschildren}">
                    </c:when>
                    <c:when test="${navMenuLevel == 1 && !hasChildren}">
                        <li class="${liclass1}">
                    </c:when>
                    <c:otherwise>
                        <li class="${liclass2}">
                    </c:otherwise>
                </c:choose>
                <%--if level, path and type matches, display menu and its children--%>
                <template:module node="${menuItem}" view="${menuItemTemplate}" editable="false"/>
                <c:if test="${hasChildren}">
                    <template:include view="custom">
                        <template:param name="base" value="${menuItem.identifier}"/>
                        <template:param name="navMenuLevel" value="${navMenuLevel + 1}"/>
                        <template:param name="omitFormatting" value="true"/>
                    </template:include>
                </c:if>
                </li>
            </c:if>
        </c:when>
        <c:otherwise>
            <c:if test="${hasChildren}">
                <c:choose>
                    <c:when test="${navMenuLevel == 1}">
                        <li class="${liclass1}&nbsp;${liclasshaschildren}">
                    </c:when>
                    <c:otherwise>
                        <li class="${liclass2}">
                    </c:otherwise>
                </c:choose>
                <template:include view="simple">
                    <template:param name="custom" value="${menuItem.identifier}"/>
                    <template:param name="navMenuLevel" value="${navMenuLevel + 1}"/>
                    <template:param name="omitFormatting" value="true"/>
                </template:include>
                </li>
            </c:if>
        </c:otherwise>
    </c:choose>

</c:forEach>
</ul>
<c:if test="${navMenuLevel == 1}">
    <c:if test="${!empty openingtag}"></ ${openingtag}>
    </c:if>
</c:if>