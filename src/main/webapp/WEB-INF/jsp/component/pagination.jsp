<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<core:set var="currentPage" value="${page.number + 1}"/>
<div class="position-relative">
    <div class="navigation d-flex justify-content-center align-items-center my-4">
        <div class="pagination">
            <core:if test="${!page.first}">
                <a class="first" href="${url}#${id}">
                    &lt;&lt;
                </a>
            </core:if>
            <core:if test="${page.hasPrevious()}">
                <a class="previous" rel="prev" href="${url}?page=${currentPage - 1}#${id}">
                    &lt;
                </a>
            </core:if>
            <span class="current">${currentPage}</span>
            <core:if test="${page.hasNext()}">
                <a class="next" href="${url}?page=${currentPage + 1}#${id}">
                    &gt;
                </a>
            </core:if>
            <core:if test="${!page.last}">
                <a class="last" href="${url}?page=${page.totalPages}#${id}">
                    &gt;&gt;
                </a>
            </core:if>
        </div>
    </div>
    <div class="position-absolute top-0 end-0 text-end align-items-center">page ${page.number + 1} sur ${page.totalPages}</div>
</div>


