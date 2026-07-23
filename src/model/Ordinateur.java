package model;

import java.sql.*;
import java.util.*;

public class Ordinateur {
    int id;
    int idModele;
    int RAM;
    String processeur;
    int disqueDur;

    public Ordinateur() {
    }

    public Ordinateur(int id, int idModele, int RAM, String processeur, int disqueDur) {
        this.id = id;
        this.idModele = idModele;
        this.RAM = RAM;
        this.processeur = processeur;
        this.disqueDur = disqueDur;
    }

    public Ordinateur(int idModele, int RAM, String processeur, int disqueDur) {
        this.idModele = idModele;
        this.RAM = RAM;
        this.processeur = processeur;
        this.disqueDur = disqueDur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int rAM) {
        RAM = rAM;
    }

    public String getProcesseur() {
        return processeur;
    }

    public void setProcesseur(String processeur) {
        this.processeur = processeur;
    }

    public int getDisqueDur() {
        return disqueDur;
    }

    public void setDisqueDur(int disqueDur) {
        this.disqueDur = disqueDur;
    }

    public void save() {
        try {
            Connection con = ConnexionDB.getConnexion();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ordinateur (idModele, ram, processeur, disqueDur) VALUES (?, ?, ?, ?)");
            ps.setInt(1, this.getIdModele());
            ps.setInt(2, this.getRAM());
            ps.setString(3, this.getProcesseur());
            ps.setInt(4, this.getDisqueDur());

            int lignesModifiees = ps.executeUpdate();

            if (lignesModifiees > 0) {
                System.out.println("Succès : L'ordinateur a bien été enregistré en BDD !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Ordinateur> findAll() {
        List<Ordinateur> liste = new ArrayList<>();
        try {
            Connection con = ConnexionDB.getConnexion();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM ordinateur");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ordinateur o = new Ordinateur(
                        rs.getInt("id"),
                        rs.getInt("idModele"),
                        rs.getInt("ram"),
                        rs.getString("processeur"),
                        rs.getInt("disqueDur"));
                liste.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public List<Modele> findAllModeles() {
        List<Modele> liste = new ArrayList<>();
        try {
            Connection con = ConnexionDB.getConnexion();
            PreparedStatement ps = con.prepareStatement("SELECT id, libelle, reference FROM modele");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Modele m = new Modele(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getString("reference"));
                liste.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public Ordinateur findById(int id) {
        Ordinateur ordinateur = null;
        try {
            Connection con = ConnexionDB.getConnexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordinateur WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ordinateur = new Ordinateur(
                        rs.getInt("id"),
                        rs.getInt("idModele"),
                        rs.getInt("ram"),
                        rs.getString("processeur"),
                        rs.getInt("disqueDur"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordinateur;
    }

    public void update() {
        try {
            Connection con = ConnexionDB.getConnexion();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE ordinateur SET idModele = ?, ram = ?, processeur = ?, disqueDur = ? WHERE id = ?");
            ps.setInt(1, this.getIdModele());
            ps.setInt(2, this.getRAM());
            ps.setString(3, this.getProcesseur());
            ps.setInt(4, this.getDisqueDur());
            ps.setInt(5, this.getId());

            int lignesModifiees = ps.executeUpdate();

            if (lignesModifiees > 0) {
                System.out.println("Succès : L'ordinateur a bien été mis à jour en BDD !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            Connection con = ConnexionDB.getConnexion();
            String sql = "DELETE FROM ordinateur WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.getId());

            int lignesModifiees = ps.executeUpdate();

            if (lignesModifiees > 0) {
                System.out.println("Succès : L'ordinateur a bien été supprimé de la BDD !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveEtat(int idOrdi, int idEtat, java.sql.Date dateEtat, String observation) {
        String sql = "INSERT INTO ordinateur_etat (idOrdi, idEtat, date, observation) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnexionDB.getConnexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrdi);
            ps.setInt(2, idEtat);
            ps.setDate(3, dateEtat);
            ps.setString(4, observation);

            int lignesModifiees = ps.executeUpdate();

            if (lignesModifiees > 0) {
                System.out.println("Succès : L'état de l'ordinateur a bien été enregistré en BDD !");
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'enregistrement de l'état :");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erreur générale :");
            e.printStackTrace();
        }
    }

    public int getCountOrdiParEtat(java.sql.Date date, int etat) {
        try {
            Connection con = ConnexionDB.getConnexion();
            if (con == null) {
                System.out.println("la connexion est nulle.");
                return -2; 
            }

            PreparedStatement ps = con.prepareStatement(
                    "select count(*) as total from ordinateur_etat where idEtat = ? AND date = ?");

            ps.setInt(1, etat);
            ps.setDate(2, date);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int getCountOrdiParTypePanne(java.sql.Date date, int typePanne) {
        try {
            Connection con = ConnexionDB.getConnexion();
            if (con == null) {
                System.out.println("la connexion est nulle.");
                return -2; 
            }

            PreparedStatement ps = con.prepareStatement(
                    "select count(*) as total_type from ordinateur_etat where idEtat = ? AND idTypePanne = ? AND date = ?");
            ps.setInt(1, 2);
            ps.setInt(2, typePanne);
            ps.setDate(3, date);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_type");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {

        Ordinateur ordi = new Ordinateur();
        String date = "2026-07-23";
        java.sql.Date date_etat = java.sql.Date.valueOf(date);

        int count_en_panne = ordi.getCountOrdiParEtat(date_etat, 1);
        System.out.println("Nombre de modèles récupérés : " + count_en_panne);

        Ordinateur o = new Ordinateur(0, 1, 16, "Intel i7", 512);
        o.save();

        // List<Ordinateur> liste = o.findAll();
        // for (Ordinateur ord : liste) {
        // System.out.println("ID: " + ord.getId() + ", ID Modele: " + ord.getIdModele()
        // + ", RAM: " + ord.getRAM() + ", Processeur: " + ord.getProcesseur() + ",
        // Disque Dur: " + ord.getDisqueDur());
        // }

        List<Modele> listeModeles = o.findAllModeles();
        System.out.println("Nombre de modèles récupérés : " + listeModeles.size());
    }
}
