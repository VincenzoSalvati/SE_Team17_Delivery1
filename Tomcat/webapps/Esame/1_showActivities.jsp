<%@ page import = "java.sql.*" %>
<%@ page import = "JAVA.*" %>
<%@ include file = "config.jsp" %>

<%
   	String jsonResult;

    MySqlDbConnection db = new MySqlDbConnection();
    db.setDbUser(dbUser);
    db.setDbPassword(dbPassword);
    db.setDbName(dbName);

    ShowActivitiesBrowseService service = new ShowActivitiesBrowseService();
    
    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
    //out.println(jsonResult);
%>

<%= service.getShowActivitiesBrowseToJSON(db) %>