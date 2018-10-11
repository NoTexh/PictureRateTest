<%--
  Created by IntelliJ IDEA.
  User: xNoTe
  Date: 11.10.2018
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <img src="${pageContext.request.contextPath}/images/Shinoichi">

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
