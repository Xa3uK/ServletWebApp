package net.servlet.soloservlets;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Simple servlet that demonstrates file uploading using org.apache.commons.
 *
 * @author Eugene Suleimanov
 */
@MultipartConfig
public class Files extends HttpServlet {

    static final int fileMaxSize = 100 * 1024;
    static final int memMaxSize = 100 * 1024;
    private final String filePath = "/Users/xa3uk/Desktop/hillel/servproselyte/src/main/resources/uploads/";

    public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);

        try {
            final Collection<Part> parts = req.getParts();
            for (final Part part : parts) {
                part.write("/Users/xa3uk/Desktop/hillel/servproselyte/src/main/resources/" + part.getSubmittedFileName());
            }
            response.getWriter().print("The file has been uploaded successfully.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Upload failed.");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}

