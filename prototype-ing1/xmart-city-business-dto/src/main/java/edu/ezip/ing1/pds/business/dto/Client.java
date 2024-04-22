package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "client")
public class Client {

    private Integer id_client;
    private String nom_client;
    private String prenom_client;
    private Date date_de_naissance_client;
    private BigDecimal poids;
    private String genre;
    private Integer taille;
    private String numero_de_telephone_client;
    private String mail_client;
    private String ville;
    private String adresse;
    private String code_Postal;

    public Client() {
    }
    public final Client build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet,"id_client","nom_client", "prenom_client", "date_de_naissance_client", "poids", "genre", "taille", "numero_de_telephone_client", "mail_client", "ville", "adresse", "code_Postal");
        return this;
    }
    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, id_client, nom_client, prenom_client, date_de_naissance_client, poids, genre, taille, numero_de_telephone_client, mail_client, ville, adresse, code_Postal);
    }


    public Client(Integer id_client, String nom_client, String prenom_client, Date date_de_naissance_client, BigDecimal poids, String genre, Integer taille, String numero_de_telephone_client, String mail_client, String ville, String adresse, String code_Postal) {
        this.id_client = id_client;
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.date_de_naissance_client = date_de_naissance_client;
        this.poids = poids;
        this.genre = genre;
        this.taille = taille;
        this.numero_de_telephone_client = numero_de_telephone_client;
        this.mail_client = mail_client;
        this.ville = ville;
        this.adresse = adresse;
        this.code_Postal = code_Postal;
    }

    public Integer getId_client() {
        return id_client;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public Date getDate_de_naissance_client() {
        return date_de_naissance_client;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getTaille() {
        return taille;
    }

    public String getNumero_de_telephone_client() {
        return numero_de_telephone_client;
    }

    public String getMail_client() {
        return mail_client;
    }

    public String getVille() {
        return ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCode_Postal() {
        return code_Postal;
    }

    @JsonProperty("Id_client")
    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }

    @JsonProperty("Nom_client")
    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    @JsonProperty("Prenom_client")
    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    @JsonProperty("Date_de_naissance_client")
    public void setDate_de_naissance_client(Date date_de_naissance_client) {
        this.date_de_naissance_client = date_de_naissance_client;
    }

    @JsonProperty("Poids")
    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    @JsonProperty("Genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @JsonProperty("Taille")
    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    @JsonProperty("Numero_de_telephone_client")
    public void setNumero_de_telephone_client(String numero_de_telephone_client) {
        this.numero_de_telephone_client = numero_de_telephone_client;
    }

    @JsonProperty("Mail_client")
    public void setMail_client(String mail_client) {
        this.mail_client = mail_client;
    }

    @JsonProperty("Ville")
    public void setVille(String ville) {
        this.ville = ville;
    }

    @JsonProperty("Adresse")
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @JsonProperty("Code_Postal")
    public void setCode_Postal(String code_Postal) {
        this.code_Postal = code_Postal;
    }

    private void setFieldsFromResulset(final ResultSet resultSet, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for(final String fieldName : fieldNames ) {
            final Field field = this.getClass().getDeclaredField(fieldName);
            field.set(this, resultSet.getObject(fieldName));
        }
    }

    private final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement, final Object ... fieldNames)
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;
        for(final Object fieldName : fieldNames) {
            preparedStatement.setObject(++ix, fieldName);
        }
        return preparedStatement;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id_client='" + id_client + '\'' +
                ", Nom_client='" + nom_client + '\'' +
                ", Prenom_client='" + prenom_client + '\'' +
                ", Date_de_naissance_client='" + date_de_naissance_client + '\'' +
                ", Poids='" + poids + '\'' +
                ", Genre='" + genre + '\'' +
                ", Taille='" + taille + '\'' +
                ", Numero_de_telephone_client='" + numero_de_telephone_client + '\'' +
                ", Mail_client='" + mail_client + '\'' +
                ", Ville='" + ville + '\'' +
                ", Adresse='" + adresse + '\'' +
                ", Code_Postal='" + code_Postal + '\'' +
                '}';
    }
}