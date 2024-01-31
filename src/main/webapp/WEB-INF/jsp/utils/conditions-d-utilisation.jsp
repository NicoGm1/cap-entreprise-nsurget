<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Error"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>
<div class="container pt-5">
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
            <h1 class="text-center">Conditions d'Utilisation</h1>
            <div class="content-padding-2-5-2">
            <p><strong>Dernière mise à jour :</strong> [Date]</p>

            <h2>1. Acceptation des Conditions</h2>
            <p>1.1 En utilisant ce site, vous acceptez les présentes conditions d'utilisation. Si vous n'acceptez pas ces conditions, veuillez ne pas utiliser le site.</p>

            <h2>2. Utilisation du Contenu</h2>
            <p>2.1 Le contenu de ce site est destiné à des fins d'information et de divertissement. Vous ne pouvez pas utiliser le contenu à des fins illégales ou non autorisées.</p>

            <h2>3. Compte Utilisateur</h2>
            <p>3.1 Vous êtes responsable de la sécurité de votre compte utilisateur. Ne partagez pas vos informations d'identification avec d'autres personnes.</p>

            <h2>4. Contenu Utilisateur</h2>
            <p>4.1 En soumettant du contenu sur ce site, vous garantissez que le contenu est original et que vous avez le droit de le publier. Le site se réserve le droit de supprimer tout contenu enfreignant les règles communautaires.</p>

            <h2>5. Confidentialité</h2>
            <p>5.1 Consultez notre <a href="[URL_vers_politique_de_confidentialite]">Politique de Confidentialité</a> pour comprendre comment nous collectons, utilisons et protégeons vos informations.</p>

            <h2>6. Modifications des Conditions</h2>
            <p>6.1 Nous nous réservons le droit de modifier ces conditions d'utilisation à tout moment. Les modifications seront publiées sur le site avec la date de mise à jour.</p>

            <footer class="mt-5 text-muted text-center">
                <p>Contactez-nous si vous avez des questions ou des préoccupations concernant nos conditions d'utilisation.</p>
            </footer>
            </div>
        </div>

    </div>
</div>

<%@ include file="../footer.jsp" %>