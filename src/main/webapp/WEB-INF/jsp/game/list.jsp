<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Liste des jeux"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container main">
    <h1 class="title-with-margin">Les jeux !</h1>
    <div class="bg-dark-rounded-body">
        <c:if test="${not empty games}">
        <div class="content-padding-2-5-2">
            <h2 class="mx-3 mb-3">Tous les jeux : 🎮
                <security:authorize access="hasRole('ROLE_MODERATOR')">
                <a href="${UrlRoute.URL_GAME_POST}" class="ms-2">
                    <i class="fa fa-circle-plus link-green"></i>
                </a>
            </security:authorize></h2>
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
                    <div class="col-lg-6 col-md-12 col-sm-12 mt-3">
                        <div class="main-game-card w-100 rounded">
                            <div class="card rounded mb-2">
                                <div class="row g-0 bg-dark rounded">
                                    <div class="col-md-5">
                                        <a class="btn-link" href="${UrlRoute.URL_GAME}/${game.slug}">
                                        <img src="${game.image}" class="img-fluid img-game-card rounded-start" alt="...">
                                        </a>
                                    </div>
                                    <div class="col-md-7 d-flex flex-column">
                                        <div class="card-body">
                                            <a class="btn-link" href="${UrlRoute.URL_GAME}/${game.slug}">
                                            <h5 class="card-title">${game.name}</h5>
                                            </a>
                                            <div class="content-description">
                                                <p class="inner-description">
                                                    <span>${game.description}</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="ms-3 mt-auto"> <!-- Utilisez mt-auto pour aligner en bas -->
                                            <p>
                                                <span class="${jspUtils.getCssClas(game.rating())}">${game.rating()}</span>
                                                <span> / 20 </span>
                                            </p>
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


                        <security:authorize access="hasRole('ROLE_MODERATOR')">
                            <div class="Moderator-option row">
                                <span class="col-3 d-flex align-items-center mt-auto"><small class="text-body-secondary">Option de moderation </small></span>
                                <span class="col-8 d-flex align-items-center mt-auto ps-3">
                                        <a href="${UrlRoute.URL_GAME_DELETE}/${game.slug}">
                                            <button type="submit" class="btn btn-danger">Supprimer</button>
                                        </a>
                                        <a href="${UrlRoute.URL_GAME_PUT}/${game.slug}">
                                            <button type="submit" class="btn btn-secondary ms-3">Modifier</button>
                                        </a>

                                    </span>
                            </div>
                        </security:authorize>
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
        <security:authorize access="hasRole('ROLE_MODERATOR')">
            <a href="${UrlRoute.URL_GAME_POST}" class="nav-link ms-2">
                Ajouter un jeu
            </a>
        </security:authorize></h2>
    </div>

</div>

<%@ include file="../footer.jsp" %>
