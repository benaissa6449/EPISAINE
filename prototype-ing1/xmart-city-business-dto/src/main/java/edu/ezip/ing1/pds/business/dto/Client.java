package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonRootName(value = "student")
public class Client {
    private String id;
    private String nom;
    private String prenom;
    private String datedenaissance;
    private String poids;
    private String genre;
    private String taille;
    private String numerotelephone;
    private String mail;
    private String ville;
    private String adresse;
    private String codepostal;

    public Client() {
    }
    public final Client build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet, "name", "firstname","group");
        return this;
    }
    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, id, nom, prenom, datedenaissance, poids, genre, taille, numerotelephone, mail, ville, adresse, codepostal);
    }
    public Client(String id, String nom, String prenom, String datedenaissance, String poids, String genre, String taille, String numerotelephone, String mail, String ville, String adresse, String codepostal) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.datedenaissance = datedenaissance;
        this.poids = poids;
        this.genre = genre;
        this.taille = taille;
        this.numerotelephone = numerotelephone;
        this.mail = mail;
        this.ville = ville;
        this.adresse = adresse;
        this.codepostal = codepostal;
    }

    // Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDatedenaissance(String datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public void setNumerotelephone(String numerotelephone) {
        this.numerotelephone = numerotelephone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDatedenaissance() {
        return datedenaissance;
    }

    public String getPoids() {
        return poids;
    }

    public String getGenre() {
        return genre;
    }

    public String getTaille() {
        return taille;
    }

    public String getNumerotelephone() {
        return numerotelephone;
    }

    public String getMail() {
        return mail;
    }

    public String getVille() {
        return ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCodepostal() {
        return codepostal;
    }

    private void setFieldsFromResulset(final ResultSet resultSet, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for(final String fieldName : fieldNames ) {
            final Field field = this.getClass().getDeclaredField(fieldName);
            field.set(this, resultSet.getObject(fieldName));
        }
    }
    private final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;
        for(final String fieldName : fieldNames ) {
            preparedStatement.setString(++ix, fieldName);
        }
        return preparedStatement;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date de naissance='" + datedenaissance + '\'' +
                ", poids='" + poids + '\'' +
                ", genre='" + genre + '\'' +
                ", taille='" + taille + '\'' +
                ", numero de telephone='" + numerotelephone + '\'' +
                ", mail='" + mail + '\'' +
                ", ville='" + ville + '\'' +
                ", adresse='" + adresse + '\'' +
                ", code postal='" + codepostal + '\'' +
                '}';
    }
}