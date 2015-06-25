/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.servlet;

import com.google.gson.Gson;
import hwcore.modules.java.src.library.common.SysConfig;
import hwcore.modules.java.src.library.database.RecordSet;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.main.controller.ControllerMain;
import system.news.model.TableModelNewsList;
import system.user.model.EntityModelUser;
import system.user.model.HandlerUserQuery;
import system.user.model.UserRS;

/**
 *
 * @author giuseppe
 */
public class RestApi extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        SysConfig.setServletContext(this.getServletContext());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * GET requests can be cached GET requests remain in the browser history GET
     * requests can be bookmarked GET requests should never be used when dealing
     * with sensitive data GET requests have length restrictions GET requests
     * should be used only to retrieve data
     *
     * Commonly used to sending data for storage or protected requests For a
     * RESTFul architecture, you can use tunneling on DELETE/PUT actions
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);

        ControllerMain.I(); // initialize system

        //Enumeration<String> parameterNames = request.getParameterNames();
        try {
            String table = req.getParameter("table");
            String type = req.getParameter("type");

            ArrayList<RecordSet> rs = new ArrayList<>();
            switch (table) {
                case "news":
                    rs = NewsHandler.handleNewsGet(req, resp, type);
                    break;
                case "user":
                    UserRS uRS = UserHandler.handleUserGet(req, resp, type);
                    if (uRS != null) {
                        rs.add(uRS);
                    }
                    break;
            }

            if (rs.size() <= 0) {
                resp.getWriter().write("false");
                return;
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String result = new Gson().toJson(rs);
            resp.getWriter().write("{\"table\" : " + result + "}");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * POST requests are never cached POST requests do not remain in the browser
     * history POST requests cannot be bookmarked POST requests have no
     * restrictions on data length
     *
     * Commonly used to sending data for storage or protected requests For a
     * RESTFul architecture, you can use tunneling on DELETE/PUT actions to
     * support legacy browsers
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /*
         * TUNNELING
         */
        try {
            String method = req.getHeader("X-HTTP-Method-Override");
            if (method == null) {
                method = "POST"; // default value if not override defined
            }

            switch (method) {
                case "PUT":
                    doPut(req, resp);
                    break;
                case "DELETE":
                    doDelete(req, resp);
                    break;
                case "POST":
                default:
                    handlePost(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    private void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
        String table = req.getParameter("table");
        String type = req.getParameter("type");

        switch (table) {
            case "user":
                UserHandler.handleUserPost(req, resp, type);
                break;
        }
    }

    /**
     * [ DO DATA DELETE ]
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        // we don't have to call super method because it checks DELETE method
        //super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.

        // DO DELETE
        String table = req.getParameter("table");
        String type = req.getParameter("type");

        switch (table) {
            case "user":
                UserHandler.handleUserDelete(req, resp, type);
                break;
        }
    }

    /**
     * [ DO DATA INSERT ]
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        //super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.

        // DO UPDATE
        String table = req.getParameter("table");
        String type = req.getParameter("type");

        switch (table) {
            case "user":
                UserHandler.handleUserPut(req, resp, type);
                break;
        }
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
