<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Liste en attente - GameReview"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<div class="container">

    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
            <div class="content-padding-2-5-2 pt-2 bg-dark rounded" >
                <h1 class="title-with-margin">${user.nickname}</h1>
                <div class="row">
                    <div class="col-6 border-end text-end">
                        <p>Pseudo : ${user.nickname}</p>
                        <p>Rôle : ${user.getRole()}</p>

                    </div>
                    <div class="col-6">
                        <c:if test="${user.getRole() eq 'Joueur'}">
                        <p>Nombre de Review : ${userReviews.totalElements}</p>
                            <c:if test="${user.rating() != null}">
                        <p>Moyenne des avis : ${user.rating()}</p>
                            </c:if>
                        </c:if>
                        <c:if test="${user.getRole() eq 'Moderateur'}">
                            <img src="https://img.freepik.com/photos-premium/chat-est-patron-principal-costume-dans-chaise-cuir-regardant-camera-portrait-homme-affaires-bureau-chef-entreprise-drole-genere-par-ia_287527-1417.jpg" alt="Image Rigolote de chat" class="rounded-4 img-review-what">
                        </c:if>
                    </div>
                </div>
            </div>
            <c:if test="${not empty userReviews.content}">
            <h2 class="mt-5">Les ${userReviews.totalElements} reviews en ligne de ${user.nickname} : <i class="fa-solid fa-ghost"></i></h2>
            <c:set var="page" scope="request" value="${userReviews}"/>
            <c:set var="url" scope="request" value="${currentPath}"/>
            <%@ include file="../component/pagableReview.jsp" %>
            <%@ include file="../component/pagination.jsp" %>
            </c:if>
<security:authorize access="hasRole('ROLE_MODERATOR')">
            <c:if test="${not empty page_waiting_review.content}">
            <h2>Ces ${page_waiting_review.totalElements} reviews en cours de moderation : ⌚</h2>

            <c:set var="page" scope="request" value="${page_waiting_review}"/>
            <c:set var="url" scope="request" value="${currentPath}"/>
            <%@ include file="../component/pagableReview.jsp" %>
            <%@ include file="../component/pagination.jsp" %>
            </c:if>
</security:authorize>
            <c:if test="${user.getRole() eq 'Moderateur'}">
                <h1 class="title-with-margin display-1">Moderateur</h1>

                <div class="row mt-3 shield-user">
                    <div class="col-6 text-end">
                        <i class="m-2 fas fa-shield-alt fa-5x"></i>
                    </div>
                    <div class="col-6">
                        <i class="m-2 fas fa-user fa-5x"></i>
                    </div>

                </div>
            </c:if>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
