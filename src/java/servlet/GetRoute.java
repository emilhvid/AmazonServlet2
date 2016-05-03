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
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author jose
 */
public class GetRoute extends HttpServlet {

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
       // System.out.println("XXXXX doGet getroutsdsdsde");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetRoute</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetRoute at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
      private ButikSøgning bs;
      
      @Override
      public void init(ServletConfig c) throws ServletException {
        System.out.println("XXXXX doGet getroutxxxxxe");
        URL url;
        try {
            url = new URL("http://52.37.83.173:9901/amazonConnection?wsdl");
            QName qname = new QName("http://amazonconnection/", "ButikSøgningImplService");
            Service service = Service.create(url, qname);
            bs = service.getPort(ButikSøgning.class);    } 
        catch (MalformedURLException ex) {
            
        }
    }    
    
      
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet: " + "originLocation" + "time" + "destLocation" + "date");
        //System.out.println("XXXXX doGet getroute");
         response.setContentType("text/html");
         
        PrintWriter out = response.getWriter();
      if(request.getParameter("orginLocation" + "destLocation"+"time" +
           "date")!=null& request.getParameter("city")!=null){
          
      String title = "trip "+request.getParameter("orginLocation"+ "destLocation"+"time" +
           "date")+ " in "+request.getParameter("city");
      String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
      out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
//                "  <li><b>originLocation</b>: " + request.getParameter("destLocation") + "\n" +
//              "  <li><b>Time</b>: " + "  <li><b>Date</b>: "
//               + request.getParameter("city") + "\n" +
                "</ul>\n" +
                "</body></html>");
          
          List<String> add = bs.getRoute(request.getParameter("originLocation"), request.getParameter("destLocation"),
                  request.getParameter("time"), request.getParameter("date"),true);
           System.out.println("doGet: " + "originLocation"+"time"+"destLocation"+ "date");
          for (String string : add) {
              out.print(string+"<br>");
          }
      } 
    }

    
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
