package model;

import java.sql.*;

public class Utilisateur {
    private int id;
    private String login;
    private String mdp;
    private String role;

    public Utilisateur() {
    }

    public Utilisateur(int id, String login, String mdp, String role) {
        this.id = id;
        this.login = login;
        this.mdp = mdp;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public boolean checkLogin (String login, String mdp){
        Connection con = ConnexionDB.getConnexion();
        try {
            String sql = "SELECT * FROM utilisateur WHERE login = ? AND mdp = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, mdp);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public Utilisateur findUtilisateurConnecte (String login, String mdp) {
        Utilisateur utilisateur = null;
        Connection con = ConnexionDB.getConnexion();
        try {
            String sql = "SELECT * FROM utilisateur WHERE login = ? AND mdp = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, mdp);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                utilisateur = new Utilisateur(
                    rs.getInt("id"),
                    rs.getString("login"),
                    rs.getString("mdp"),
                    rs.getString("role")
                );

                return utilisateur;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}