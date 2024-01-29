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
    <script type="text/javascript" src="${contextPath}/js/lib/bootstrap/bootstrap.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script type="text/javascript" src="../js/lib/bootstrap/bootstrap.bundle.js"></script>
    <script type="text/javascript" src="../js/lib/bootstrap/bootstrap.esm.js"></script>

</head>
<body class="body">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-navbar">
    <div class="row">
        <div class="col-5 d-flex align-items-center">
            <a href="${UrlRoute.URL_HOME}"><img class="logo-navbar m-3 pl-3"
                                                src="https://i.ibb.co/3ygCD6S/Title-Game.png"
                                                alt="Logo Home"
                                                title="Logo Home"/></a>
        </div>
        <div class="col-6 d-flex align-items-center">
            <div class="d-flex search-bar">
                <input type="text" class="form-control" placeholder="Jeux, Editeur, Platforme ..."
                       data-search-bar>
                <a class="my-auto m-2">
                    <i class="fa fa-magnifying-glass"></i>
                </a>
            </div>
            <div class="search-response-container">
            </div>

        </div>
        <div class="col-1 d-flex align-items-center">
            <div class="col-10">

            </div>
            <div class="col-2 row text-end">
                <security:authorize access="!isAuthenticated()">
                    <a class="nav-link" href="${UrlRoute.URL_REGISTER}">Inscription</a>
                    <a class="nav-link" href="${UrlRoute.URL_LOGIN}">Connexion</a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <div class="dropdown text-end">
                        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                ${userLogged.getRole()} : ${userLogged.nickname}
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${UrlRoute.URL_USER}/${userLogged.nickname}">Mon compte</a></li>
                            <security:authorize access="hasRole('ROLE_GAMER')">
                            <li><a class="dropdown-item" href="${UrlRoute.URL_REVIEW_OWN_LIST}">Mes reviews</a></li>
                            <li><a class="dropdown-item" href="${UrlRoute.URL_REVIEW_POST}">Ajouter un commentaire</a></li>
                            </security:authorize>
                            <security:authorize access="hasRole('ROLE_MODERATOR')">
                                <li><a class="dropdown-item" href="${UrlRoute.URL_REVIEW_MODERATOR}">Reviews à Moderer</a></li>
                            </security:authorize>
                            <li>
                                <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                                    <button class="dropdown-item" type="submit" tabindex="3">Logout</button>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                            </li>
                        </ul>
                    </div>
                </security:authorize>
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