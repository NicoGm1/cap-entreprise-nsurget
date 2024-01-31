<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Téléverser une image"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container mt-5">
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">

    <h1 class="mb-5">Téléverser une image pour ${game.name}</h1>

    <c:set var="label" scope="request" value=""/>
    <c:set var="urlAction" value="${currentUrl}"/>
    <%@ include file="../component/form-upload.jsp" %>
            <div class="col-lg-6 col-md-12 col-sm-12 mt-5">
                <div class="main-game-card w-100 rounded">
                    <div class="card rounded mb-2">
                        <div class="row g-0 mt bg-dark rounded">
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

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>