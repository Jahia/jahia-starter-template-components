<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="jahia" uri="http://www.jahia.org/tags/templateLib" %>

<jcr:nodeProperty node="${currentNode}" name="j:node" var="reference"/>
<jcr:nodeProperty node="${currentNode}" name="j:target" var="target"/>
<jcr:nodeProperty node="${currentNode}" name="j:linknode" var="linkreference"/>
<jcr:nodeProperty node="${currentNode}" name="j:alternateText" var="alt"/>
<jcr:nodeProperty node="${currentNode}" name="jcr:title" var="titleAttribute"/>
<jcr:nodeProperty node="${currentNode}" name="j:url" var="externalUrl"/>
<jcr:nodeProperty node="${currentNode}" name="j:linkTitle" var="linkTitle"/>
<jcr:nodeProperty node="${currentNode}" name="j:classes" var="classAttribute"/>
<jcr:nodeProperty node="${currentNode}" name="j:style" var="styleAttribute"/>
<jcr:nodeProperty node="${currentNode}" name="height" var="heightAttribute"/>
<jcr:nodeProperty node="${currentNode}" name="width" var="widthAttribute"/>
<jcr:nodeProperty node="${currentNode}" name="additionalAttributes" var="moreAttributes"/>


<c:set var="node" value="${reference.node}"/>
<c:if test="${not empty node}">
    <jahia:addCacheDependency node="${node}" />
    <c:url var="imageUrl" value="${node.url}" context="/"/>


    <c:if test="${not empty target.string}"><c:set var="target"> target="${target.string}"</c:set></c:if>
    <c:set var="linknode" value="${linkreference.node}"/>
    <c:if test="${not empty linknode}">
        <c:url var="linkUrl" value="${url.base}${linknode.path}.html"/>
        <c:set var="linkTitle"> title="${linknode.displayableName}"</c:set>
    </c:if>
    <c:if test="${empty linkUrl and not empty externalUrl}">
        <c:if test="${!functions:matches('^[A-Za-z]*:.*', externalUrl.string)}"><c:set var="protocol">http://</c:set></c:if>
        <c:url var="linkUrl" value="${protocol}${externalUrl.string}"/>
        <c:if test="${not empty linkTitle.string}"><c:set var="linkTitle"> title="${linkTitle.string}"</c:set></c:if>
    </c:if>
    <c:if test="${!empty linkUrl}">
        <a href="${linkUrl}" ${target} ${linkTitle}>
    </c:if>

    <c:if test="${!empty classAttribute}">
        <c:set var="class" value="class=\"${fn:escapeXml(classAttribute)}\""/>
    </c:if>

    <c:if test="${!empty styleAttribute}">
        <c:set var="style" value="style=\"${fn:escapeXml(styleAttribute)}\""/>
    </c:if>

    <c:set var="width" value="width=\"100%\""/>
    <c:if test="${!empty widthAttribute}">
        <c:set var="width" value="width=\"${fn:escapeXml(widthAttribute)}\""/>
    </c:if>

    <c:if test="${!empty heightAttribute}">
        <c:set var="height" value="height=\"${fn:escapeXml(heightAttribute)}\""/>
    </c:if>

    <c:if test="${!empty titleAttribute}">
        <c:set var="title" value="title=\"${fn:escapeXml(titleAttribute)}\""/>
    </c:if>

    <img src="${imageUrl}"
        alt="${fn:escapeXml(not empty alt.string ? alt.string : currentNode.name)}"
        <c:out value="${title}" escapeXml="false" />
        <c:out value="${class}" escapeXml="false" />
        <c:out value="${style}" escapeXml="false" />
        <c:out value="${width}" escapeXml="false" />
        <c:out value="${height}" escapeXml="false" />
        <c:out value="${moreAttributes}" escapeXml="false" />
         />
    <c:if test="${!empty linkUrl}">
        </a>
    </c:if>
</c:if>
<c:if test="${empty node}">
	<c:if test="${not empty reference}">
		<jahia:addCacheDependency path="${reference.string}" />
	</c:if>
	<c:if test="${renderContext.editMode}">
    	<fmt:message key="label.empty"/>
    </c:if>
</c:if>
