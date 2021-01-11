<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<%--@elvariable id="headResources" type="java.util.List"--%>
<%--@elvariable id="resource" type="org.jahia.modules.sitebuilder.taglibs.model.Resource"--%>


<c:set var="navMenuType" value="${currentNode.properties['navMenuType'].string}"/>
<c:set var="ulTag" value="${navMenuType eq 'list' ? 'ul' : 'div'}"/>
<c:set var="liTag" value="${navMenuType eq 'list' ? 'li' : 'div'}"/>

<c:set var="navRootPage" value="${jcr:getParentOfType(currentNode, 'jnt:page')}"/>
<c:set var="navPages" value="${jcr:getChildrenOfType(navRootPage, 'jnt:page')}"/>
<c:set var="currentPagePath" value="${renderContext.mainResource.node.path}"/>

<c:if test="${not empty navPages}">

    <nav class="${currentNode.properties['navClasses']}">
    <${ulTag} class="${currentNode.properties['lvl1ListClasses']}">
    <c:forEach var="node" items="${navPages}">
        <c:set var="children" value="${jcr:getChildrenOfType(node, 'jnt:page')}"/>
        <c:set var="showLvl2Pages" value="${(not empty children) && currentNode.properties['showLvl2Pages'].boolean}"/>
        <c:set var="lvl1classes" value="${currentNode.properties['lvl1ItemClasses']}
            ${ (currentPagePath eq node.path) ? currentNode.properties['currentPageClasses'] : ''}
            ${ showLvl2Pages ? currentNode.properties['hasSubpagesClasses'] : '' }"/>

        <${liTag} class="${lvl1classes}">
        <c:url value="${node.url}" var="nodeUrl"/>
        <a href="${nodeUrl}">${node.displayableName}</a>
        <c:if test="${showLvl2Pages}">
            <${ulTag} class="${currentNode.properties['lvl2ListClasses']}">
            <c:forEach var="child" items="${children}">
                <${liTag} class="${currentNode.properties['lvl2ItemClasses']}"
                    ${ (currentPagePath eq node.path) ? currentNode.properties['currentPageClasses'] : ''}>

                <c:url value="${child.url}" var="childUrl"/>
                <a href="${childUrl}">${child.displayableName}</a>
                </${liTag}>
            </c:forEach>
            </${ulTag}>
        </c:if>
        </${liTag}>

    </c:forEach>
    </${ulTag}>
    </nav>

</c:if>
