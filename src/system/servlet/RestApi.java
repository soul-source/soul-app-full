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
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.main.controller.ControllerMain;
import system.news.model.TableModelNewsList;
import system.user.model.EntityModelUser;
import system.user.model.HandlerUserQuery;
import system.user.model.TableModelUser;
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SysConfig.setServletContext(this.getServletContext());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        ControllerMain.I(); // initialize system

        //Enumeration<String> parameterNames = request.getParameterNames();
        try {
            String table = request.getParameter("table");

            ArrayList<RecordSet> rs = new ArrayList<>();
            switch (table) {
                case "news":
                    TableModelNewsList newsList = new TableModelNewsList();
                    newsList.refreshList("");
                    rs = new ArrayList<>(newsList.getTableData().getRecords());
                    break;
                case "user":
                    HandlerUserQuery uQuery = new HandlerUserQuery(EntityModelUser.I());

                    int id = Integer.parseInt(request.getParameter("user-id"));
                    UserRS uRs=uQuery.loadUtente(id);
                    Date d=(java.sql.Date)uRs.getValue(EntityModelUser.I().DATA_DI_NASCITA.getId());
                    Long ms=d.getTime();
                    uRs.setValue(EntityModelUser.I().DATA_DI_NASCITA.getId(), ms);
                    rs.add(uRs);
                    break;
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String result = new Gson().toJson(rs);
            response.getWriter().write("{\"table\" : " + result + "}");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
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
        // DO INSERT/CREATE

        String table = request.getParameter("table");
        String type = request.getParameter("type");

        switch (table) {
            case "user":
                handleUser(request, response, type);
                break;
        }

    }

    private void handleUser(HttpServletRequest request, HttpServletResponse response, String type) throws IOException {
        switch (type) {
            case "session":
                int sId = Integer.parseInt(request.getParameter("id"));
                System.out.println(sId);
                String sToken = request.getParameter("token");
                HandlerUserQuery uQuery = new HandlerUserQuery(EntityModelUser.I());

                UserRS uRs = uQuery.loadUtente(sId);
                response.getWriter().write(
                        Boolean.toString(sToken.equals(uRs.getSessionToken()))
                );
                break;
            case "reg":
                String regEmail = request.getParameter("email");
                String regPassword = request.getParameter("pass");
                String regName = request.getParameter("name");
                String regLastName = request.getParameter("lastName");
                String regBornDate = request.getParameter("bornDate");

                new HandlerUserQuery(EntityModelUser.I())
                        .regUser(regName, regLastName, regBornDate, regEmail, regPassword);

                response.getWriter().write("Registrazione Effettuata!");
                break;
            case "login":
                String loginEmail = request.getParameter("email");
                String loginPassword = request.getParameter("pass");
                String loginToken = request.getParameter("sessionTok");

                int userId = new HandlerUserQuery(EntityModelUser.I())
                        .loginUser(loginEmail, loginPassword, loginToken);

                response.getWriter().write(Integer.toString(userId));
                break;
            case "profile":
                String pId = request.getParameter("id");
                String pPassword = request.getParameter("password");
                String pName = request.getParameter("name");
                String pLastName = request.getParameter("lastName");
                String pBornDate = request.getParameter("birthDay");
                String pCity = request.getParameter("city");
                String pCap = request.getParameter("cap");
                String pStreet = request.getParameter("street");
                String pCountry = request.getParameter("country");
                String pTaxCode = request.getParameter("taxCode");

                new HandlerUserQuery(EntityModelUser.I())
                        .updateUser(pId, pPassword, pName, pLastName, pBornDate,
                                pCity, pCap, pStreet, pCountry, pTaxCode);

                response.getWriter().write("Profilo aggiornato!");
                break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.

        // DO DELETE
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.

        // DO UPDATE
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
