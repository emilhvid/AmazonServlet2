/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Client.ButikSøgning;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Emil
 */
public class GetAdresses extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetAdresses</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetAdresses at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    private ButikSøgning bs;

    @Override
    public void init(ServletConfig c) throws ServletException {
        URL url;
        try {
            url = new URL("http://52.37.83.173:9901/amazonConnection?wsdl");
            QName qname = new QName("http://amazonconnection/", "ButikSøgningImplService");
            Service service = Service.create(url, qname);
            bs = service.getPort(ButikSøgning.class);    } 
        catch (MalformedURLException ex) {
            
        }
    }    
            
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void doGet(HttpServletRequest request, 
	                  HttpServletResponse response) throws IOException
	{
        // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      if(request.getParameter("name")!=null& request.getParameter("city")!=null){
      String title = "avalible "+request.getParameter("name")+" in "+request.getParameter("city");
      String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
      out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
//                "  <li><b>First Name</b>: "
//                + request.getParameter("name") + "\n" +
//                "  <li><b>Last Name</b>: "
//                + request.getParameter("city") + "\n" +
                "</ul>\n" +
                "</body></html>");
      
          List<String> add = bs.getAdresses(request.getParameter("city"), request.getParameter("name"));
          for (String string : add) {
              out.print(string+"<br>");
          }
      }
	}


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
