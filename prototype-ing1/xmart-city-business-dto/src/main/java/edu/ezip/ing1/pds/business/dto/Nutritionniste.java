package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonRootName(value = "nutritionniste")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutritionniste {
    private Integer id_nutritionniste;
    private String nom_n;
    private String prenom_n;
    private String numero_de_telephone_n;
    private String mail_n;

    public Nutritionniste() {
    }

    public final Nutritionniste build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResultSet(resultSet,"id_nutritionniste",  "nom_n", "prenom_n", "numero_de_telephone_n", "mail_n");
        return this;
    }

    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, id_nutritionniste, nom_n, prenom_n, numero_de_telephone_n, mail_n);
    }

    public Nutritionniste(Integer id_nutritionniste, String nom_n, String prenom_n, String numero_de_telephone_n, String mail_n) {
        this.id_nutritionniste = id_nutritionniste;
        this.nom_n = nom_n;
        this.prenom_n = prenom_n;
        this.numero_de_telephone_n = numero_de_telephone_n;
        this.mail_n = mail_n;
    }

    public Integer getId_nutritionniste() {
        return id_nutritionniste;
    }

    @JsonProperty("Id_nutritionniste")
    public void setId_nutritionniste(Integer id_nutritionniste) {
        this.id_nutritionniste = id_nutritionniste;
    }

    public String getNom_N() {
        return nom_n;
    }

    @JsonProperty("Nom_N")
    public void setNom_N(String nom_n) {
        this.nom_n = nom_n;
    }

    public String getPrenom_N() {
        return prenom_n;
    }

    @JsonProperty("Prenom_N")
    public void setPrenom_N(String prenom_n) {
        this.prenom_n = prenom_n;
    }

    public String getNumero_de_telephone_N() {
        return numero_de_telephone_n;
    }

    @JsonProperty("Numero_de_telephone_N")
    public void setNumero_de_telephone_N(String numero_de_telephone_n) {
        this.numero_de_telephone_n = numero_de_telephone_n;
    }

    public String getMail_N() {
        return mail_n;
    }

    @JsonProperty("Mail_N")
    public void setMail_N(String mail_n) {
        this.mail_n = mail_n;
    }

    private void setFieldsFromResultSet(final ResultSet resultSet, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for(final String fieldName : fieldNames ) {
            final Field field = this.getClass().getDeclaredField(fieldName);
            field.set(this, resultSet.getObject(fieldName));
        }
    }

    private final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement, final Object ... fieldValues)
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;
        for(final Object fieldValue : fieldValues) {
            preparedStatement.setObject(++ix, fieldValue);
        }
        return preparedStatement;
    }

    @Override
    public String toString() {
        return "nutritionniste{" +
                "Id_nutritionniste='" + id_nutritionniste + '\'' +
                ", Nom_N='" + nom_n + '\'' +
                ", Prenom_N=" + prenom_n +
                ", Numero_de_telephone_N='" + numero_de_telephone_n + '\'' +
                ", Mail_N='" + mail_n + '\'' +
                '}';
    }
}
