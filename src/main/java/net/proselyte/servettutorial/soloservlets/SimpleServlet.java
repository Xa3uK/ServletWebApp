package net.proselyte.servettutorial.soloservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter messageWriter = response.getWriter();
        messageWriter.println("<h1>" + "fffffffrrr" + "<h1>");
    }

    public void destroy() {

    }
}

