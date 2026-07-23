package servlet;

import model.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

public class OrdiInfoServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

            if (session == null || session.getAttribute("utilisateur") == null) {
                req.setAttribute("erreur", "Veuillez vous connecter pour accéder aux autres pages.");
                req.getRequestDispatcher("/ordi/login.jsp").forward(req, res);
                return; 
            }

        String path = req.getServletPath();
        Ordinateur util = new Ordinateur();
        java.sql.Date date = java.sql.Date.valueOf(req.getParameter("date"));


        int count_fonctionnel = util.getCountOrdiParEtat(date, 1);
        int count_en_panne = util.getCountOrdiParEtat(date, 2);

        int count_alimentation = util.getCountOrdiParTypePanne(date, 1);
        int count_disque = util.getCountOrdiParTypePanne(date, 2);
        int count_carteM = util.getCountOrdiParTypePanne(date, 3);


        req.setAttribute("count_fonctionnel", count_fonctionnel);
        req.setAttribute("count_en_panne", count_en_panne);

        req.setAttribute("count_alimentation", count_alimentation);
        req.setAttribute("count_disque", count_disque);
        req.setAttribute("count_carteM", count_carteM);

        RequestDispatcher dispatcher = req.getRequestDispatcher("ordi/info.jsp");
        dispatcher.forward(req, res);



    }

}
