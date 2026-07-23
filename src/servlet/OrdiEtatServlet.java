package servlet;

import model.*;

import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class OrdiEtatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        int idOrdinateur = Integer.parseInt(req.getParameter("id_ordinateur"));
        int etat = Integer.parseInt(req.getParameter("etat"));
        String observations = req.getParameter("observations");
        java.sql.Date date = java.sql.Date.valueOf(req.getParameter("date"));

        Ordinateur ordinateur = new Ordinateur();
        ordinateur.saveEtat(idOrdinateur, etat, date, observations);
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(
            "{\"success\":true,\"message\":\"État enregistré\"}"
        );
    }
}
