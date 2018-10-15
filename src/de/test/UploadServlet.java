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

@WebServlet(name = "UploadServlet", value = "/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Resource(name="jdbc/db")
    private DataSource dataSource;
    PrintWriter out;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String SQL_UPLOAD = "INSERT INTO picture(data, name, uploaddate) VALUES (?,?,NOW())";
        out = response.getWriter();
        int result = 0;

        Part part = request.getPart("image");
        String name = request.getParameter("name");

        if(part != null){
            try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_UPLOAD)){

                InputStream is = part.getInputStream();

                statement.setBlob(1, is);
                statement.setString(2,name);

                result = statement.executeUpdate();
                if(result > 0){
                    out.println("<h1>image inserted sucessfully</h1>");
                }
            } catch (SQLException e) {
                out.println(e);
            }
        }
    }
}
