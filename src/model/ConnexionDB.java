package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionDB {

    private static String URL = "jdbc:mysql://localhost:3306/gestionParc";
    private static String UTILISATEUR = "user";
    private static String MOT_DE_PASSE = "password";

    public static Connection getConnexion() {
        Connection connexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
        } catch (Exception e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
        return connexion;
    }
}