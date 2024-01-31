<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<%
    Object title = request.getAttribute("title");
    if (title == null) {
        title = "GameReview";
    }
    request.setAttribute("title", title);
%>

<html>
<head>
    <title>${title}</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
    <link href="${contextPath}/css/main.css" rel="stylesheet">
    <link rel="icon" href="https://assets.stickpng.com/images/5a18871c8d421802430d2d05.png">
    <script type="text/javascript" src="${contextPath}/js/page/search-bar.js"></script>
    <script type="text/javascript" src="${contextPath}/js/component/sortPage.js"></script>
    <script type="text/javascript" src="${contextPath}/js/component/hideForm.js"></script>
    <script type="text/javascript" src="${contextPath}/js/component/select.js"></script>
    <script type="text/javascript" src="${contextPath}/js/component/alert.js"></script>
    <script type="text/javascript" src="${contextPath}/js/lib/bootstrap/bootstrap.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script type="text/javascript" src="../js/lib/bootstrap/bootstrap.bundle.js"></script>
    <script type="text/javascript" src="../js/lib/bootstrap/bootstrap.esm.js"></script>

</head>
<body class="body">
<nav class="navbar navbar-expand-lg ">
    <div class="content-bar">
    <div class="row">
        <div class="col-3 d-flex align-items-center m-3">
            <a href="${UrlRoute.URL_HOME}"><img class="logo-navbar m-3 pl-3"
                                                src="https://i.ibb.co/3ygCD6S/Title-Game.png"
                                                alt="Logo Home"
                                                title="Logo Home"/></a>
        </div>
        <div class="col-5 d-flex align-items-center">
            <security:authorize access="isAuthenticated()">
            <div class="d-flex search-bar">
                <input type="text" class="form-control" placeholder="Jeux, Editeur, Platforme ..."
                       data-search-bar>
                <a class="my-auto m-2">
                    <i class="fa fa-magnifying-glass"></i>
                </a>
            </div>
            <div class="search-response-container">
            </div>
            </security:authorize>
        </div>
        <div class="col-3 d-flex align-items-center ms-5 nav-menu">
                <security:authorize access="!isAuthenticated()">
                    <div class="col-9"></div>
                    <div class="col-1 row text-end">
                    <a class="nav-link" href="${UrlRoute.URL_REGISTER}">Inscription</a>
                    <a class="nav-link" href="${UrlRoute.URL_LOGIN}">Connexion</a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                        <div class="col-6"></div>
                        <div class="col-1 row text-end">
                    <div class="dropdown">
                        <ul class="dropdown-menu">
                            <security:authorize access="hasRole('ROLE_GAMER')">
                                <li><a class="dropdown-item" href="${UrlRoute.URL_REVIEW_POST}">Ajouter un commentaire</a></li>
                                <li><a class="dropdown-item" href="${UrlRoute.URL_REVIEW_OWN_LIST}">Mes reviews</a></li>
                            </security:authorize>
                            <li><a class="dropdown-item ms" href="${UrlRoute.URL_USER}/${userLogged.slug}">Mon compte</a></li>
                            <li><a class="dropdown-item" href="${UrlRoute.URL_GAME}">Liste des jeux</a></li>
                            <security:authorize access="hasRole('ROLE_MODERATOR')">
                                <li><a class="dropdown-item" href="${UrlRoute.URL_REVIEW_MODERATOR}">Reviews à Moderer</a></li>
                                <li><a class="dropdown-item" href="${UrlRoute.URL_GAME_POST}">Ajouter un jeu</a></li>
                            </security:authorize>
                            <li>
                                <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off" class="logout-button">
                                    <button class="dropdown-item logout-button" type="submit" tabindex="3">Logout</button>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                            </li>
                        </ul>
                        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                ${userLogged.getRole()} : ${userLogged.nickname}
                        </a>
                    </div>
                </security:authorize>
            </div>
        </div>
    </div>
    </div>
<%--    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"--%>
<%--            data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"--%>
<%--            aria-expanded="false" aria-label="Toggle navigation">--%>
<%--        <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>
<%--    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">--%>
<%--        <div class="navbar-nav">--%>

<%--        </div>--%>
<%--    </div>--%>

</nav>
<div class="navbar-color-line bg-red">
</div>
<c:if test="${requestScope.get('flashMessage') != null}">
<div class="container">

        <div class="alert alert-${flashMessage.type} m-3">
                ${flashMessage.message}
        </div>
</div>
</c:if>
