<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.sql.*" isThreadSafe="false" %>
<%@ page import = "java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<%
    String sDbDrv = "com.mysql.cj.jdbc.Driver";
    String sDbUrl = "jdbc:mysql://localhost:3306/picturerate?serverTimezone=Europe/Berlin";
    String sUsr = "root";
    String sPwd = "root";
    String sTable = "picture";
    String sSql = "SELECT idpicture FROM " + sTable + " ORDER BY uploaddate LIMIT 10";

    if (request.getParameterNames().hasMoreElements() == true
            && null != sDbDrv && 0 < sDbDrv.length()
            && null != sDbUrl && 0 < sDbUrl.length()
            && null != sSql && 0 < sSql.length()) {
        Class.forName(sDbDrv);
        Connection cn = DriverManager.getConnection(sDbUrl, sUsr, sPwd);
        java.sql.Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sSql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int n = rsmd.getColumnCount();
        out.println("<table border=1 cellspacing=0><tr>");
        for (int i = 1; i <= n; i++) // Achtung: erste Spalte mit 1 statt 0
        {
            out.println("<th>" + rsmd.getColumnName(i) + "</th>");
        }
        while (rs.next()) {
            out.println("</tr><tr>");
            String test = "${pageContext.request.contextPath}/latest/";
            for (int i = 1; i <= n; i++) // Achtung: erste Spalte mit 1 statt 0
            {
                out.println("<td>" + rs.getString(i) + "</td>");
                out.println("<img src=\"" + test + rs.getString(i) + " \">");
            }
        }
        out.println("</tr></table>");
        rs.close();
        st.close();
        cn.close();
    }
%>

<html>
    <head>
        <title>PictureRate</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="./css/styles.css">
    </head>
    <body>
        <header>
            <img src="res/logo.png" alt="logo">
            <nav>
                <form action="image/*" method="GET">
                    <input type="text" name="name" placeholder="Search..">
                    <input type ="submit" value="Search">
                </form>
            </nav>
        </header>

        <main>

            <body>
                <sql:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"
                                   url = "jdbc:mysql://localhost:3306/picturerate?serverTimezone=Europe/Berlin"
                                   user = "root"  password = "root"/>

                <sql:query dataSource = "${snapshot}" var = "result">
                    SELECT * from picture order by uploaddate DESC LIMIT 10;
                </sql:query>

                <c:forEach var = "row" items = "${result.rows}">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/latest/<c:out value = "${row.idpicture}"/>"><img src="${pageContext.request.contextPath}/latest/<c:out value = "${row.idpicture}"/>"></a>
                    </td>
                </tr>
            </c:forEach>

            <form action="upload" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <th align="center" colspan="5">
                            <h2>Image Upload in Database</h2>
                        </th>
                    </tr>
                    <tr>
                        <th align="right">Select Image:</th>
                        <th>
                            <input type="file" name="image">
                        </th>
                    </tr>
                    <tr>
                        <th align="right">Select Name:</th>
                        <th>
                            <input type="text" name="name">
                        </th>
                    </tr>
                    <tr>
                        <th><input type="submit" value="Upload"></th>
                        <th><input type="reset" value="Reset"></th>
                    </tr>
                </table>
            </form>
        </main>

        <footer>
            <p>Das Projekt wurde im Rahmen der Vorlesung WebProgrammierung erstellt.</p>
            <p>© 2018 by Kai Schmid, Markus Scheuring, Tobias Frietsch</p>
        </footer>

    </body>
</html>
