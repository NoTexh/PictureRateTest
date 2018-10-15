package de.test;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "GetImageServlet", value = "/images/*")
public class GetImageServlet extends HttpServlet {

    private static final String SQL_FIND_NAME = "SELECT data FROM picture WHERE name = ?";

    /*
     * Ist in der context.xml des Tomcat-Servers definiert :
     * <Resource
     *    name="jdbc/db" type="javax.sql.DataSource"
     *    maxActive="100" maxIdle="30" maxWait="10000"
     *    url="jdbc:mysql://localhost:3306/picturerate?serverTimezone=Europe/Berlin"
     *    driverClassName="com.mysql.cj.jdbc.Driver"
     *    username="root" password="root"
     * />
     */
    @Resource(name = "jdbc/db")
    private DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageName = request.getPathInfo().substring(1);
        System.out.println("imagename"+imageName);

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_NAME)) {
                statement.setString(1, imageName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    byte[] content = resultSet.getBytes("data");
                    response.setContentType(getServletContext().getMimeType(imageName));
                    System.out.println("Mimetyp" +getServletContext().getMimeType(imageName));
                    response.setContentLength(content.length);
                    response.getOutputStream().write(content);
                    connection.close();
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Something failed at SQL/DB level.", e);
        }
    }
}
