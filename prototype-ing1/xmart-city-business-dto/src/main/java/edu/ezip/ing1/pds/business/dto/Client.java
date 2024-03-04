package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonRootName(value = "client")
public class Client {
    private  String ID_Clients;
    private  String Nom_Client;
    private  String Prenom_Client;
    private  String Date_de_naissance_Client;
    private  String Poids;
    private  String Genre;
    private  String Taille;
    private  String Numero_de_telephone_Client;
    private  String Mail_Client;
    private  String Ville;
    private  String Adresse;
    private  String Code_Postal_;

    public Client() {
    }
    public final Client build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet, "ID_Clients", "Nom_Client", "Prenom_Client", "Date_de_naissance_Client", "Poids", "Genre", "Taille", "Numero_de_telephone_Client", "Mail_Client", "Ville", "Adresse", "Code_Postal_");
        return this;
    }
    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, ID_Clients, Nom_Client, Prenom_Client, Date_de_naissance_Client, Poids, Genre, Taille, Numero_de_telephone_Client, Mail_Client, Ville, Adresse, Code_Postal_);
    }
    public Client(String ID_Clients, String Nom_Client, String Prenom_Client, String Date_de_naissance_Client, String Poids, String Genre, String Taille, String Numero_de_telephone_Client, String Mail_Client, String Ville, String Adresse, String Code_Postal_) {
        this.ID_Clients = ID_Clients;
        this.Nom_Client = Nom_Client;
        this.Prenom_Client = Prenom_Client;
        this.Date_de_naissance_Client = Date_de_naissance_Client;
        this.Poids = Poids;
        this.Genre = Genre;
        this.Taille = Taille;
        this.Numero_de_telephone_Client = Numero_de_telephone_Client;
        this.Mail_Client = Mail_Client;
        this.Ville = Ville;
        this.Adresse = Adresse;
        this.Code_Postal_ = Code_Postal_;
    }

    // Setter
    @JsonProperty("Id_Clients")
    public void setId_Clients(String ID_Clients) {
        this.ID_Clients = ID_Clients;
    }
    @JsonProperty("Nom_Client")
    public void setNom_Client(String Nom_Client) {
        this.Nom_Client = Nom_Client;
    }
    @JsonProperty("Prenom_Client")
    public void setPrenom_Client(String Prenom_Client) {
        this.Prenom_Client = Prenom_Client;
    }
    @JsonProperty("Date_de_naissance_Client")
    public void setDate_de_naissance_Client(String Date_de_naissance_Client) {
        this.Date_de_naissance_Client = Date_de_naissance_Client;
    }
    @JsonProperty("Poids")
    public void setPoids(String Poids) {
        this.Poids = Poids;
    }
    @JsonProperty("Genre")
    public void setGenre(String Genre) {
        this.Genre = Genre;
    }
    @JsonProperty("Taille")
    public void setTaille(String Taille) {
        this.Taille = Taille;
    }
    @JsonProperty("Numero_de_telephone_Client")
    public void setNumero_de_telephone_Client(String Numero_de_telephone_Client) {
        this.Numero_de_telephone_Client = Numero_de_telephone_Client;
    }
    @JsonProperty("Mail_Client")
    public void setMail_Client(String Mail_Client) {
        this.Mail_Client = Mail_Client;
    }
    @JsonProperty("Ville")
    public void setVille(String Ville) {
        this.Ville = Ville;
    }
    @JsonProperty("Adresse")
    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }
    @JsonProperty("Code_Postal_")
    public void setCode_Postal_(String Code_Postal_) {
        this.Code_Postal_ = Code_Postal_;
    }

    // Getter
    public String getId_Clients() {
        return ID_Clients;
    }

    public String getNom_Client() {
        return Nom_Client;
    }

    public String getPrenom_Client() {
        return Prenom_Client;
    }

    public String getDate_de_naissance_Client() {
        return Date_de_naissance_Client;
    }

    public String getPoids() {
        return Poids;
    }

    public String getGenre() {
        return Genre;
    }

    public String getTaille() {
        return Taille;
    }

    public String getNumero_de_telephone_Client() {
        return Numero_de_telephone_Client;
    }

    public String getMail_Client() {
        return Mail_Client;
    }

    public String getVille() {
        return Ville;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getCode_Postal_() {
        return Code_Postal_;
    }

    private void setFieldsFromResulset(final ResultSet resultSet, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for(final String fieldName : fieldNames ) {
            final Field field = this.getClass().getDeclaredField(fieldName);
            field.set(this, resultSet.getObject(fieldName).toString());
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
                "ID_Client='" + ID_Clients + '\'' +
                ", Nom_Client='" + Nom_Client + '\'' +
                ", Prenom_Client='" + Prenom_Client + '\'' +
                ", Date_de_naissance_Client='" + Date_de_naissance_Client + '\'' +
                ", Poids='" + Poids + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Taille='" + Taille + '\'' +
                ", Numero_de_telephone_Client='" + Numero_de_telephone_Client + '\'' +
                ", Mail_Client='" + Mail_Client + '\'' +
                ", Ville='" + Ville + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", Code_Postal_='" + Code_Postal_ + '\'' +
                '}';
    }
}