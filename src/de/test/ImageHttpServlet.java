package de.test;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.io.*;
import java.sql.*;

@WebServlet(name = "ImageHttpServlet", value = "/latest/*")
public class ImageHttpServlet extends HttpServlet {

    @Resource(name="jdbc/db")
    private DataSource dataSource;
    PrintWriter out;
    private static final String SQL_LATEST_10 = "SELECT data FROM picture WHERE idpicture = ?";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String imageName = request.getPathInfo().substring(1);
        
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_LATEST_10)) {
            statement.setString(1, imageName);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    byte[] content = resultSet.getBytes("data");
                    response.setContentType(getServletContext().getMimeType("image/png"));
                    System.out.println("Mimetyp" +getServletContext().getMimeType("image/png"));
                    response.setContentLength(content.length);
                    response.getOutputStream().write(content);
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Something failed at SQL/DB level.", e);
        }
    }
}