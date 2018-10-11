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

@WebServlet(name = "NameSearchServlet", value = "/image/*")
public class NameSearchServlet extends HttpServlet {

    @Resource(name = "jdbc/db")
    private DataSource dataSource;
    private static final String SQL_FIND_NAME = "SELECT data FROM picture WHERE name = ?";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageName = request.getParameter("name");

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_NAME)) {
            statement.setString(1, imageName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    byte[] content = resultSet.getBytes("data");
                    response.setContentType(getServletContext().getMimeType(imageName));
                    response.setContentLength(content.length);
                    response.getOutputStream().write(content);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Something failed at SQL/DB level.", e);
        }
    }
}
