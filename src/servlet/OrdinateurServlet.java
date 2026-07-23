package servlet;

import model.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

public class OrdinateurServlet extends HttpServlet { 
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getServletPath();
        Ordinateur util = new Ordinateur();
        String idString = req.getParameter("id");

        if ("/ordi/liste".equals(path)) {
            List<Ordinateur> listeOrdis = util.findAll();
            
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            
            PrintWriter out = res.getWriter();
            Gson gson = new Gson();
            String json = gson.toJson(listeOrdis); 
            out.print(json);
            out.flush();
            
            return; 
        }

        if (idString != null && !idString.isEmpty()) {
            HttpSession session = req.getSession(false);

            if (session == null || session.getAttribute("utilisateur") == null) {
                req.setAttribute("erreur", "Veuillez vous connecter pour accéder aux autres pages.");
                req.getRequestDispatcher("/ordi/login.jsp").forward(req, res);
                return; 
            }

            int id = Integer.parseInt(idString);
            Ordinateur ordi = util.findById(id);
            ordi.delete();
        }

        List<Ordinateur> listeOrdisHtml = util.findAll(); 
        req.setAttribute("listeOrdinateurs", listeOrdisHtml);
        RequestDispatcher dispatcher = req.getRequestDispatcher("ordi/liste.jsp");
        dispatcher.forward(req, res);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
            if (session.getAttribute("utilisateur") == null) {
                req.setAttribute("errorMessage", "Veuillez vous connecter pour accéder aux autres pages.");
                req.getRequestDispatcher("ordi/login.jsp").forward(req, res);
                return; 
            }

        String idOrdiString = req.getParameter("id");
        int idModele = Integer.parseInt(req.getParameter("idModele"));
        int ram = Integer.parseInt(req.getParameter("ram"));
        int disqueDur = Integer.parseInt(req.getParameter("disqueDur"));
        String processeur = req.getParameter("processeur");

        Ordinateur util = new Ordinateur();
        String path = req.getServletPath();
        
        
        if(idOrdiString == null || idOrdiString.isEmpty()) {
            Ordinateur ordiModifie = new Ordinateur(idModele, ram, processeur, disqueDur);
            ordiModifie.save();
            res.sendRedirect("ordi");
            
        } else {
            int idOrdi = Integer.parseInt(idOrdiString);
            Ordinateur ordiModifie = new Ordinateur(idOrdi, idModele, ram, processeur, disqueDur);
            ordiModifie.update();
            res.sendRedirect("ordi");
        }
    }
}
