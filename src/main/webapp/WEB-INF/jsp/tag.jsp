<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%-- Importe l'objet UrlRoute pour pouvoir l'utiliser partout dans les JSP --%>
<%@ page import="fr.nsurget.game_review.mapping.UrlRoute" %>

<%-- Imports nécessaires pour récupérer le UserService dans les JSP --%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="fr.nsurget.game_review.service.UserService" %>
<%@ page import="fr.nsurget.game_review.entity.User" %>
<%@ page import="fr.nsurget.game_review.utils.DateUtils" %>
<%@ page import="fr.nsurget.game_review.utils.JspUtils" %>

<%--&lt;%&ndash; Renomme de manière plus simple le "pageContext.request.contextPath" &ndash;%&gt;--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%
    WebApplicationContext ctx = RequestContextUtils.findWebApplicationContext(request);
    if (ctx != null) {
        UserService userService = ctx.getBean(UserService.class);
        request.setAttribute("jspUtils", ctx.getBean(JspUtils.class));
        request.setAttribute("dateUtils", ctx.getBean(DateUtils.class));
        if (request.getUserPrincipal() != null) {
            User user = userService.findByNickname(request.getUserPrincipal().getName());
            request.setAttribute("userLogged", user);
        }
    }
%>