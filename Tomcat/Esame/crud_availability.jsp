<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>
<%@ include file="config.jsp" %>

<%
    MySqlDbConnection db = new MySqlDbConnection();
    db.setDbUser(dbUser);
    db.setDbPassword(dbPassword);
    db.setDbName(dbName);

    int id = 0;
    int avail_8to9 = 0;
    int avail_9to10 = 0;
    int avail_10to11 = 0;
    int avail_11to12 = 0;
    int avail_14to15 = 0;
    int avail_15to16 = 0;
    int avail_16to17 = 0;

	if (request.getParameter("id") != null && request.getParameter("avail_8to9") != null && request.getParameter("avail_9to10") != null && request.getParameter("avail_10to11") != null
	&& request.getParameter("avail_11to12") != null && request.getParameter("avail_14to15") != null && request.getParameter("avail_15to16") != null && request.getParameter("avail_16to17") != null) {
	    id = Integer.parseInt(request.getParameter("id"));
		avail_8to9 = Integer.parseInt(request.getParameter("avail_8to9"));
		avail_9to10 = Integer.parseInt(request.getParameter("avail_9to10"));
		avail_10to11 = Integer.parseInt(request.getParameter("avail_10to11"));
		avail_11to12 = Integer.parseInt(request.getParameter("avail_11to12"));
		avail_14to15 = Integer.parseInt(request.getParameter("avail_14to15"));
		avail_15to16 = Integer.parseInt(request.getParameter("avail_15to16"));
		avail_16to17 = Integer.parseInt(request.getParameter("avail_16to17"));
    } 

	Availability avail = new Availability(db, id);
	avail.setAvail_8to9(avail_8to9);
	avail.setAvail_9to10(avail_9to10);
	avail.setAvail_10to11(avail_10to11);
	avail.setAvail_11to12(avail_11to12);
	avail.setAvail_14to15(avail_14to15);
	avail.setAvail_15to16(avail_15to16);
	avail.setAvail_16to17(avail_16to17);
	avail.update();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<%= avail.getResults() %>