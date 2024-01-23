<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Error"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>
<div class="container pt-5">
    <div class="bg-dark rounded mt-3">
        <h1 class="p-5">Oops! erreur inattendue s'est produite. 😞</h1>
        <div class="px-5">
        <p>Nous sommes désolés. Veuillez réessayer plus tard.</p>
        <p>Error : ${code}</p>
        <p>Essaye les autres lien : <a href="${UrlRoute.URL_SITEMAP}" class="btn-primary">Tous les liens disponible !</a></p>
        </div>
        <div class="p-5">
            <a href="${UrlRoute.URL_HOME}" class="btn btn-primary">Retour à la page d'accueil</a>
        </div>

    </div>
</div>

<%@ include file="../footer.jsp" %>