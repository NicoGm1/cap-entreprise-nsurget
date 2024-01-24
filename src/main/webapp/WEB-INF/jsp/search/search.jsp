<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Search : ${search}"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container">
    <h2 class="my-5 mt-5">🕵️ Resultat de la recherche : "${search}"</h2>
    <div class="row">
        <div class="col-6 p-1">
        <c:if test="${brand.size() > 0}">
            <h2>les regions :</h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Region</th>
                    <th>Code</th>
                    <th>Id</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${region}" var="r">
                    <tr>
                        <td>${r.name}</td>
                        <td>${r.code}</td>
                        <td>${r.id}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </c:if>
        </div>
        <div class="col-6 p-1">
            <c:if test="${Listing.size() > 0}">
                <h2>les regions :</h2>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>La</th>
                        <th>region</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${region}" var="r">
                        <tr>
                            <td>${r.name}</td>
                            <td>${r.code}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </c:if>
        </div>
        <div class="col-6 p-1">
            <c:if test="${Model.size() > 0}">
                <h2>les regions :</h2>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Region</th>
                        <th>Code</th>
                        <th>Id</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${region}" var="r">
                        <tr>
                            <td>${r.name}</td>
                            <td>${r.code}</td>
                            <td>${r.id}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </c:if>
        </div>

<%--        <h2 class="my-5 mt-5">Aucune resource trouvé 😭 </h2>--%>
    </div>
</div>

<%@ include file="../footer.jsp" %>