<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Home - GameReview"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container main">
    <h1 class="title-with-margin">Les jeux !</h1>
    <div class="bg-dark-rounded-body">
        <c:if test="${not empty games}">
        <div class="content-padding-2-5-2">
            <h2 class="mx-3 mb-3">Tous les jeux : 🎮</h2>
            <div class="d-flex justify-content-between">
                <div class="d-flex">
                    <c:set var="label" scope="request" value="Name"/>
                    <c:set var="sortable" value="name"/>
                    <%@ include file="../component/sortable.jsp" %>

                    <c:set var="label" scope="request" value="Editeur"/>
                    <c:set var="sortable" value="publisher"/>
                    <%@ include file="../component/sortable.jsp" %>

                    <c:set var="label" scope="request" value="Date de sortie"/>
                    <c:set var="sortable" value="publishedAt"/>
                    <%@ include file="../component/sortable.jsp" %>
                    <span class="mt-auto mb-2">
                        <a href="${currentUrl}" class="btn-link" title="Réinitialiser les filtres">
                            <i class="fa fa-filter-circle-xmark"></i>
                        </a>
                    </span>
                </div>
            </div>
            <div class="row">
                <c:forEach items="${games.content}" var="game">
                    <div class="col-lg-6 col-md-12 col-sm-12 mt-4">
                        <div class="main-game-card w-100 rounded">
                            <div class="card rounded mb-3" style="max-width: 540px;">
                                <div class="row g-0 bg-dark rounded">
                                    <div class="col-md-5">
                                        <img src="${game.image}" class="img-fluid img-game-card rounded-start" alt="...">
                                    </div>
                                    <div class="col-md-7 d-flex flex-column">
                                        <div class="card-body">
                                            <h5 class="card-title">${game.name}</h5>
                                            <div class="content-description">
                                                <p class="inner-description">
                                                    <span>${game.description}</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="ms-3 mt-auto"> <!-- Utilisez mt-auto pour aligner en bas -->
                                            <p class="card-text"><small class="text-body-secondary">Date de sortie : ${game.publishedAt}</small></p>
                                            <p class="card-text"><small class="text-body-secondary">Disponible sur : </small></p>
                                            <p class="logoContainer">
                                                <c:forEach items="${game.platforms}" var="platform">
                                                    <img src="${platform.logo}" alt="${platform.name}" title="${platform.name}" class="logoPlatform">
                                                </c:forEach>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>

            <c:set var="page" scope="request" value="${games}"/>
            <c:set var="ignoreImport" scope="request" value="true"/>
            <c:set var="url" scope="request" value="${UrlRoute.URL_GAME}"/>
            <%@ include file="../component/pagination.jsp" %>
        </div>

        </c:if>
    </div>

</div>

<%@ include file="../footer.jsp" %>
