package model;

public class Modele {
    private int id;
    private String libelle;
    private int idMarque;
    private String reference;

    // Constructeurs
    public Modele() {}

    public Modele(int id, String libelle, String reference) {
        this.id = id;
        this.libelle = libelle;
        this.reference = reference;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public int getIdMarque() { return idMarque; }
    public void setIdMarque(int idMarque) { this.idMarque = idMarque; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
}