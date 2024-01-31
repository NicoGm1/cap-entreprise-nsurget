<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Error"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>
<div class="container pt-5">
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
            <h1 class="text-center mb-5">Oops! erreur inattendue s'est produite. 😞</h1>
            <div class="row">
                <div class="col-6">
                    <p>Nous sommes désolés. Veuillez réessayer plus tard.</p>
                    <p>Error : ${code}</p>
                    <c:if test="${content} != null">
                        <p>Error message : ${content}</p>
                    </c:if>
                    <p>Essaye les autres lien : <a href="${UrlRoute.URL_SITEMAP}" class="btn-primary">Tous les liens
                        disponible !</a></p>

                    <div class="p-5">
                        <a href="${UrlRoute.URL_HOME}" class="btn btn-primary">Retour à la page d'accueil</a>
                    </div>
                </div>

                <div class="col-6">
                    <img class="error-picture"
                         src="https://http.cat/images/${code}.jpg"
                         alt="Cat error picture"
                         title="Cat error picture"/>
                </div>
            </div>
        </div>

    </div>
</div>

<%@ include file="../footer.jsp" %>