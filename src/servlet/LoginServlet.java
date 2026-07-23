package servlet;

import model.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String login = req.getParameter("login");
        String mdp = req.getParameter("mdp");
        
        Utilisateur util_user = new Utilisateur();
        Ordinateur util_ordi = new Ordinateur();

        boolean isValidLogin = util_user.checkLogin(login, mdp);

        if (isValidLogin) {
            Utilisateur user = util_user.findUtilisateurConnecte(login, mdp);

            HttpSession session = req.getSession();
            session.setAttribute("utilisateur", user);

            List<Ordinateur> listeOrdis = util_ordi.findAll();
            req.setAttribute("listeOrdinateurs", listeOrdis);
            RequestDispatcher dispatcher = req.getRequestDispatcher("ordi/liste.jsp");
            dispatcher.forward(req, res);

        } else {
            req.setAttribute("errorMessage", "Login ou mot de passe incorrect.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("ordi/login.jsp");
            dispatcher.forward(req, res);
        }
    }
}
