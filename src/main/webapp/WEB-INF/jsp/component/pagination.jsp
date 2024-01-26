<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<core:set var="currentPage" value="${page.number + 1}"/>
<div class="position-relative">
    <div class="position-absolute align-items-center">
        <a href="${UrlRoute.URL_EXPORT}" class="btn btn-link">
            <i class="fa-solid fa-file-excel me-1"></i>
            Télécharger export Excel
        </a>
    </div>
    <div class="navigation d-flex justify-content-center align-items-center my-4">
        <div class="pagination">
            <core:if test="${!page.first}">
                <core:set var="firstPage" value="page=1"/>
                <%-- currentUrl => http://localhost:8080/ (l'url de la page courante)--%>
                <%-- currentQuery => ?sort=moderator,desc (les queryParam actuels) --%>
                <%-- firstPage => page=1 (les queryParam à ajouter) --%>
                <a class="first" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, firstPage)}">
                    &lt;&lt;
                </a>
            </core:if>
            <core:if test="${page.hasPrevious()}">
                <core:set var="previousPage" value="page=${(currentPage - 1)}"/>
                <a class="previous" rel="prev" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, previousPage)}">
                    &lt;
                </a>
            </core:if>
            <span class="current">${currentPage}</span>
            <core:if test="${page.hasNext()}">
                <core:set var="nextPage" value="page=${(currentPage + 1)}"/>
                <a class="next" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, nextPage)}">
                    &gt;
                </a>
            </core:if>
            <core:if test="${!page.last}">
                <core:set var="lastPage" value="page=${page.totalPages}"/>
                <a class="last" href="${jspUtils.generateUrlFrom(currentUrl, currentQuery, lastPage)}">
                    &gt;&gt;
                </a>
            </core:if>
        </div>
    </div>
    <div class="position-absolute top-0 end-0 text-end align-items-center">page ${page.number + 1} sur ${page.totalPages}</div>
</div>


