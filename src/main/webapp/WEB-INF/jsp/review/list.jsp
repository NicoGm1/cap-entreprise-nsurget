<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="ReviewList - GameReview"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container">
    <h1 class="title-with-margin mb-5">Mes Review</h1>
    <div class="bg-dark-rounded-body">
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="https://images.unsplash.com/photo-1682686580186-b55d2a91053c?q=80&w=1975&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="First slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="https://images.unsplash.com/photo-1682686580186-b55d2a91053c?q=80&w=1975&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="Second slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="https://images.unsplash.com/photo-1682686580186-b55d2a91053c?q=80&w=1975&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="Third slide">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>



        <div class="content-padding-2-5-2" id="dernieres-reviews-en-ligne">
            <h2>Tes dernières reviews en ligne : ✅</h2>
            <div class="row">
                <c:forEach items="${page_valid_review.content}" var="review">
                    <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
                        <div class="main-review-card w-100">
                            <p class="text-center">
                                Le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}
                                par <a class="btn-link" href="#">${review.gamer.nickname}</a>
                            </p>
                            <div class="review-card w-100 d-flex flex-column">
                                <p class="review-description">
                                        ${jspUtils.excerpt(review.description, 209)}
                                </p>
                                <div class="d-flex justify-content-between mt-auto">
                                    <p class="${jspUtils.getCssClas(review.rating)}">
                                            ${review.rating} / 20
                                    </p>
                                    <a class="btn-link" href="#">
                                            ${review.game.name}
                                    </a>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:forEach>

            </div>
            <c:set var="page" scope="request" value="${page_waiting_review}"/>
            <c:set var="id" scope="request" value="dernieres-reviews-en-ligne"/>
            <c:set var="url" scope="request" value="${UrlRoute.URL_REVIEW_LIST}"/>
            <%@ include file="../component/pagination.jsp" %>
            <p class="d-flex justify-content-end mt-4">page ${page_waiting_review.number + 1} sur ${page_waiting_review.totalPages}</p>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
