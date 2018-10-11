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

        String SQL_UPLOAD = "INSERT INTO picture(data, name) VALUES (?,?)";
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


        /*String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        */

        /*
        //Speichert die Bilder erstmal lokal
        File uploads = new File("C:/Users/xNoTe/Desktop");
        File file = File.createTempFile("someFilename-", ".ext", uploads);

        Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        */


        /*
        //Für den Fall das wir es in MySQL speichern wollen
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        for(int length = 0; (length = fileContent.read(buffer)) > 0); output.write(buffer, 0, length);
        */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>shalom<h1>");
        out.flush();
    }
}
