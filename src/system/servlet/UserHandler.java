package system.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.user.model.EntityModelUser;
import system.user.model.HandlerUserQuery;
import system.user.model.UserRS;

public class UserHandler {

    public static UserRS handleUserGet(HttpServletRequest req, HttpServletResponse resp, String type) throws IOException {
        UserSession us = UserHandler.getValidSession(req);
        if (us == null) {
            return null;
        }

        HandlerUserQuery uQuery = new HandlerUserQuery(EntityModelUser.I());

        int id = Integer.parseInt(req.getParameter("user-id"));
        UserRS uRs = uQuery.loadUtente(id);
        Date d = (java.sql.Date) uRs.getValue(EntityModelUser.I().DATA_DI_NASCITA.getId());
        Long ms = d.getTime();
        uRs.setValue(EntityModelUser.I().DATA_DI_NASCITA.getId(), ms);
        return uRs;
    }

    public static void handleUserPut(HttpServletRequest req, HttpServletResponse resp, String type) throws IOException {
        switch (type) {
            case "update-profile":
                UserSession us = getValidSession(req);
                if (us == null) {
                    resp.getWriter().write("false");
                    return;
                }

                int pId = us.getUserId();
                String pPassword = req.getParameter("password");
                String pName = req.getParameter("name");
                String pLastName = req.getParameter("lastName");
                String pBirthDate = req.getParameter("birthDay");
                String pCity = req.getParameter("city");
                String pCap = req.getParameter("cap");
                String pStreet = req.getParameter("street");
                String pCountry = req.getParameter("country");
                String pTaxCode = req.getParameter("taxCode");

                if (!new HandlerUserQuery(EntityModelUser.I())
                        .updateUser(pId, pPassword, pName, pLastName, pBirthDate,
                                pCity, pCap, pStreet, pCountry, pTaxCode)) {
                    resp.getWriter().write("false");
                    return;
                }

                resp.getWriter().write("true");
                break;
        }
    }

    public static void handleUserPost(HttpServletRequest req, HttpServletResponse resp, String type) throws IOException {
        switch (type) {
            case "session":
                boolean isValid = getValidSession(req) != null;
                resp.getWriter().write(Boolean.toString(isValid));
                break;
            case "reg":
                String regEmail = req.getParameter("email");
                String regPassword = req.getParameter("pass");
                String regName = req.getParameter("name");
                String regLastName = req.getParameter("lastName");
                String regBirthDate = req.getParameter("birthDate");

                try {
                    new HandlerUserQuery(EntityModelUser.I())
                            .regUser(regName, regLastName, regBirthDate, regEmail, regPassword);
                } catch (Exception e) {
                    resp.getWriter().write("Dati errati");
                    return;
                }

                resp.getWriter().write("Registrazione Effettuata!");
                break;
            case "login":
                String loginEmail = req.getParameter("email");
                String loginPassword = req.getParameter("pass");

                HandlerUserQuery handler = new HandlerUserQuery(EntityModelUser.I());

                UserRS u = handler.loadUtente(loginEmail, loginPassword);
                if (u == null) {
                    resp.getWriter().write("false");
                    return;
                }

                int userId = u.getId();

                String lToken = loginEmail + " " + loginPassword + " " + userId;

                // Here we should encrypt token before send to updateSession
                handler.updateSession(userId, lToken);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                String json = "{"
                        + "\"token\" : \"" + lToken + "\", "
                        + "\"id\": \"" + userId + "\""
                        + "}";
                resp.getWriter().write(json);
                break;
        }
    }

    static void handleUserDelete(HttpServletRequest req, HttpServletResponse resp, String type) throws IOException {
        UserSession us = UserHandler.getValidSession(req);
        if (us == null) {
            resp.getWriter().write("false");
            return;
        }

        HandlerUserQuery uQuery = new HandlerUserQuery(EntityModelUser.I());
        uQuery.deleteUser(us.getUserId());
    }

    public static UserSession getValidSession(HttpServletRequest req) {
        UserSession us = getSessionFromRequest(req);
        if (checkUserSession(us.getUserId(), us.getSessionToken(), req.getSession())) {
            return us;
        }

        return null;
    }

    public static boolean checkUserSession(int id, String token, HttpSession session) {
        if (session.isNew() || session.getAttribute("session-token") == null) {
            HandlerUserQuery uQuery = new HandlerUserQuery(EntityModelUser.I());

            UserRS uRs = uQuery.loadUtente(id);

            boolean ok = token.equals(uRs.getSessionToken());

            if (ok) {
                session.setAttribute("session-token", token);
                session.setAttribute("user-id", id);
            }

            return ok;
        }

        String tk = session.getAttribute("session-token").toString();
        int uid = Integer.parseInt(session.getAttribute("user-id").toString());

        if (tk.equals(token) && uid == id) {
            return true;
        } else {
            session.invalidate();
            return false;
        }
    }

    /**
     * this function retrieve user session information from passed data or
     * cookies
     *
     * @param requestretrieveUserData
     * @return
     */
    public static UserSession getSessionFromRequest(HttpServletRequest req) {
        int id = 0;
        String token = null;

        String _id = req.getParameter("user-id");
        try {
            id = Integer.parseInt(_id);
        } catch (Exception e) {
            id = 0;
        }

        token = req.getParameter("session-token");

        Cookie ck[] = req.getCookies();
        if ((id == 0 || token == null) && (ck != null && ck.length > 0)) {
            for (Cookie c : ck) {
                switch (c.getName()) {
                    case "user-id":
                        if (id == 0) {
                            id = Integer.parseInt(c.getValue());
                        }
                        break;
                    case "session-token":
                        if (token == null) {
                            try {
                                token = URLDecoder.decode(c.getValue(), "utf8");
                            } catch (UnsupportedEncodingException ex) {
                                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                }
            }
        }

        return new UserSession(token, id);
    }

    private static class UserSession {

        private String sessionToken = null;
        private int userId = 0;

        public UserSession(String sessionToken, int userId) {
            this.sessionToken = sessionToken;
            this.userId = userId;
        }

        public String getSessionToken() {
            return sessionToken;
        }

        public int getUserId() {
            return userId;
        }
    }
}
