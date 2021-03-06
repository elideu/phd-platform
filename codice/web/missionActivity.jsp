<%-- 
    Document   : missionActivity
    Created on : 26-dic-2014, 17.33.00
    Author     : gemmacatolino
--%>
<%@page import="it.unisa.dottorato.phdProfile.missions.Mission"%>
<%@page import="it.unisa.dottorato.phdProfile.missions.MissionManager"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.dottorato.account.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:choose>
    <c:when test="${sessionScope.account == null}">
        <c:redirect url="login.jsp" />
    </c:when>
</c:choose>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Xenon Boostrap Admin Panel" />
        <meta name="author" content="" />

        <title>DISTRA-MIT Dottorato</title>

        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
        <link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/xenon-core.css">
        <link rel="stylesheet" href="assets/css/xenon-forms.css">
        <link rel="stylesheet" href="assets/css/xenon-components.css">
        <link rel="stylesheet" href="assets/css/xenon-skins.css">
        <link rel="stylesheet" href="assets/css/custom.css">  
        <link rel="stylesheet" href="style/dottorato.css">

        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="script/index.js"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->2
    </head>
    <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <% Account loggedPerson = ((Account) session.getAttribute("account"));
                        if (loggedPerson.getTypeAccount().equals("phdstudent")) {
                    %> 
    <body class="page-body">

        <!-- Inclusione della pagina contenente il menù superiore --> 
        <jsp:include page="barraMenu.jsp" flush="true"/> 
        <div class="page-container">

            <!-- Inclusione della pagina contenente il menù laterale --> 
            
             <% 
                List<Mission> missions = MissionManager.getInstance().getAllMissionsOf((PhdStudent)loggedPerson);

            %>

            <div class="main-content" id="content">

                <div class="row">

                    <div class="col-sm-1"></div>

                    <div class="col-sm-10">
                        <!--Qui chiama servlet update che prende infomazioni person--> 

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <ul class="nav nav-tabs">
                                    <li role="presentation"><a href="publicationActivity.jsp">Pubblicazioni</a></li>
                                    <li role="presentation"><a href="collaborationActivity.jsp">Collaborazioni</a></li>
                                    <li role="presentation" class="active"><a href="missionActivity.jsp">Mission</a></li>
                                </ul>
                                <br>
                                <button type="button" class="btn btn-xs" aria-label="Left Align" onclick="location.href = 'addMission.jsp'">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span> Aggiungi mission
                                </button>


                            </div>
                            <div class="panel-body">
                                <table class="table table-hover" width="98%" align="center" >
                                    <thead>
                                    <th>Luogo</th>
                                    <th>Descrizione</th>		
                                    <th>Data Di Inizio</th>
                                    <th>Data Di Fine</th>
                                    </thead>
                                    
                                    <% for (Mission mission : missions) {%>

                                    <tr>
                                        <td><%= mission.getPlace()%></td>
                                        <td><%= mission.getDescription()%></td>		
                                        <td><%= mission.getStartDate()%></td>
                                        <td><%= mission.getEndDate()%></td>
                                        
                                         <% session.setAttribute("idMission", mission.getIdMission());%>



                                        <td width="20px"> <button type="button" class="btn btn-white" title="modifica">
                                                <span class="glyphicon glyphicon-cog" aria-hidden="true"onclick="location.href = 'editMission.jsp'" ></span>
                                            </button>
                                        </td>
                                        <td width="20px">
                                            <button type="button" class="btn btn-white"title="delete">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true" onclick="location.href = '<%= "DeleteMissionServlet?id=" + mission.getIdMission() %>'" ></span>
                                            </button>
                                        </td>
                                    </tr>
                                    <% }%>
                                
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-1"></div>

                </div>
            </div>

        </div>
                                    <%}else{%>
                <c:redirect url="index.jsp" />
          <%  }
    %>
            </c:when>
        </c:choose>
    </body>
</html>

