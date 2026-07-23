package servlet;

import model.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShowFormOrdiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getServletPath();
        Ordinateur util = new Ordinateur();

        List<Modele> listeModeles = util.findAllModeles();
        req.setAttribute("listeModeles", listeModeles);

        String idString = req.getParameter("id");

        
        if (idString != null ) {
            
            HttpSession session = req.getSession(false);
            if (session.getAttribute("utilisateur") == null) {
                req.setAttribute("errorMessage", "Veuillez vous connecter pour accéder aux autres pages.");
                req.getRequestDispatcher("ordi/login.jsp").forward(req, res);
                return; 
            }

            int id = Integer.parseInt(idString);
            Ordinateur ordi = util.findById(id);
            req.setAttribute("ordinateur", ordi);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("ordi/form.jsp");
        dispatcher.forward(req, res);
    }
}
