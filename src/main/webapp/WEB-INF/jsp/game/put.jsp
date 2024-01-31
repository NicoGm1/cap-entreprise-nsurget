<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${gameDTO.name} Modification"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
            <h1 class="text-center main-title">Modifier ${game.name}</h1>
            <div class="justify-content-md-center">

                <f:form modelAttribute="gameDTO" method="post" action="${UrlRoute.URL_GAME_PUT}/${game.slug}"
                        class="form" cssClass="p-5 col-lg-8 col-md-10 col-sm-12 mx-auto">
                <div class="mb-3 row">
                    <div class="col-lg-3 col-md-3 col-sm-12 text-end">Nom*
                        </div>
                            <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
                                <f:input type="text" path="name" class="form-control" placeholder="Nom du jeu"/>
                                <f:errors path="name" cssClass="invalid-feedback"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-lg-3 col-md-3 col-sm-12 text-end">
                              Date de sortie
                            </div>
                            <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
                                <f:input type="date" path="publishedAt"
                                         class="form-control"
                                         autofocus="true"
                                />
                                <f:errors path="publishedAt" cssClass="invalid-feedback"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-lg-3 col-md-3 col-sm-12 text-end">
                                Editeur*
                            </div>
                            <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
                                <!-- Utilisez 'itemValue' et 'itemLabel' pour spécifier le champ 'name' -->
                                <f:select path="publisher"
                                          items="${publishers}"
                                          cssClass="form-select"
                                          itemLabel="name"
                                >
                                </f:select>
                                <f:errors path="publisher" cssClass="invalid-feedback"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-lg-3 col-md-3 col-sm-12 text-end">
                              Description*
                            </div>
                            <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
                                <f:textarea cssClass="form-control" path="description"
                                            placeholder="Description du jeu"/>
                                <f:errors path="description" cssClass="invalid-feedback"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-lg-3 col-md-3 col-sm-12 text-end">
                              Genre*
                            </div>
                            <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
                                <!-- Utilisez 'itemValue' et 'itemLabel' pour spécifier le champ 'name' -->
                                <f:select path="genre"
                                          items="${genres}"
                                          cssClass="form-select"
                                          itemLabel="name"
                                >
                                </f:select>
                                <f:errors path="genre" cssClass="invalid-feedback"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-lg-3 col-md-3 col-sm-12 text-end">
                              Classification*
                            </div>
                            <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
                                <!-- Utilisez 'itemValue' et 'itemLabel' pour spécifier le champ 'name' -->
                                <f:select path="classification"
                                          items="${classifications}"
                                          cssClass="form-select"
                                          itemLabel="name"
                                >
                                </f:select>
                                <f:errors path="classification"
                                          cssClass="invalid-feedback"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-lg-3 col-md-3 col-sm-12 text-end">
                              Plateforme(s)*
                            </div>
                            <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
                                <input class="form-control" data-multiple-select-input="platforms"/>
                                <f:select path="platforms"
                                          items="${platforms}"
                                          cssClass="form-select"
                                          itemLabel="name"
                                          data-multiple-select="platforms"
                                >
                                </f:select>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-lg-3 col-md-3 col-sm-12 text-end">
                              Modèle économique*
                            </div>
                            <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
                                <!-- Utilisez 'itemValue' et 'itemLabel' pour spécifier le champ 'name' -->
                                <f:select path="businessModel"
                                          items="${businessModels}"
                                          cssClass="form-select"
                                          itemLabel="name"
                                >
                                </f:select>
                                <f:errors
                                        path="businessModel"
                                        cssClass="invalid-feedback"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-lg-3 col-md-2 col-sm-12 text-end">
                              Trailer Youtube
                            </div>
                            <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
                                <f:input type="text"
                                         path="trailerYt"
                                         class="form-control"
                                         placeholder="Trailer Youtube"/>
                                <f:errors
                                        path="trailerYt"
                                        cssClass="invalid-feedback"/>
                            </div>
                        </div>
                  <div class="mt-3 row">
                    <div class="col-lg-3 col-md-2 col-sm-12 text-end">

                    </div>
                    <div class="col-lg-8 col-md-8 col-sm-12">
                        <button class="btn btn-lg btn-primary btn-block"
                                type="submit">Modifier
                        </button>
                  </div>
                        </f:form>
                    </div>
                </div>

            </div>

<%@ include file="../footer.jsp" %>