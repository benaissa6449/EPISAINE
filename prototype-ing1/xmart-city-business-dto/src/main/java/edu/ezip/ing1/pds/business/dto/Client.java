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
    private String nom_Client;
    private String prenom_Client;
    private Date date_de_naissance_Client;
    private BigDecimal poids;
    private String genre;
    private Integer taille;
    private String numero_de_telephone_Client;
    private String mail_Client;
    private String ville;
    private String adresse;
    private String code_Postal;

    public Client() {
    }
    public final Client build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet,"id_client","nom_Client", "prenom_Client", "date_de_naissance_Client", "poids", "genre", "taille", "numero_de_telephone_Client", "mail_Client", "ville", "adresse", "code_Postal");
        return this;
    }
    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, id_client, nom_Client, prenom_Client, date_de_naissance_Client, poids, genre, taille, numero_de_telephone_Client, mail_Client, ville, adresse, code_Postal);
    }
    

    public Client(Integer id_client, String nom_Client, String prenom_Client, Date date_de_naissance_Client, BigDecimal poids, String genre, Integer taille, String numero_de_telephone_Client, String mail_Client, String ville, String adresse, String code_Postal) {
        this.id_client = id_client;
        this.nom_Client = nom_Client;
        this.prenom_Client = prenom_Client;
        this.date_de_naissance_Client = date_de_naissance_Client;
        this.poids = poids;
        this.genre = genre;
        this.taille = taille;
        this.numero_de_telephone_Client = numero_de_telephone_Client;
        this.mail_Client = mail_Client;
        this.ville = ville;
        this.adresse = adresse;
        this.code_Postal = code_Postal;
    }

    public Integer getId_client() {
        return id_client;
    }

    public String getNomClient() {
        return nom_Client;
    }

    public String getPrenomClient() {
        return prenom_Client;
    }

    public Date getDate_de_naissance_Client() {
        return date_de_naissance_Client;
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

    public String getNumero_de_telephone_Client() {
        return numero_de_telephone_Client;
    }

    public String getMail_Client() {
        return mail_Client;
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

    @JsonProperty("id_client")
    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }

    @JsonProperty("Nom_Client")
    public void setnomClient(String nom_Client) {
        this.nom_Client = nom_Client;
    }
    @JsonProperty("Prenom_Client")
    public void setprenomClient(String prenom_Client) {
        this.prenom_Client = prenom_Client;
    }
    @JsonProperty("Date_de_naissance_Client")
    public void setdateDeNaissanceClient(Date date_de_naissance_Client) {
        this.date_de_naissance_Client = date_de_naissance_Client;
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
    @JsonProperty("Numero_de_telephone_Client")
    public void setNumero_de_telephone_Client(String numero_de_telephone_Client) {
        this.numero_de_telephone_Client = numero_de_telephone_Client;
    }
    @JsonProperty("Mail_Client")
    public void setMail_Client(String mail_Client) {
        this.mail_Client = mail_Client;
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
                "ID_Client='" + id_client + '\'' +
                ", Nom_Client='" + nom_Client + '\'' +
                ", Prenom_Client='" + prenom_Client + '\'' +
                ", Date_de_naissance_Client='" + date_de_naissance_Client + '\'' +
                ", Poids='" + poids + '\'' +
                ", Genre='" + genre + '\'' +
                ", Taille='" + taille + '\'' +
                ", Numero_de_telephone_Client='" + numero_de_telephone_Client + '\'' +
                ", Mail_Client='" + mail_Client + '\'' +
                ", Ville='" + ville + '\'' +
                ", Adresse='" + adresse + '\'' +
                ", Code_Postal='" + code_Postal + '\'' +
                '}';
    }

    public String[] getValue() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy,mm-dd");
        String date = dateFormat.format(date_de_naissance_Client);
        String [] res = {id_client.toString(), nom_Client, prenom_Client, date, poids.toString(), genre, taille.toString(), numero_de_telephone_Client, mail_Client, ville, adresse, code_Postal};
        return res;
    }
}
