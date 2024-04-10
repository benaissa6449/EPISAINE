package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonRootName(value = "nutritionniste")
public class Nutritionniste {
    private String nom_N;
    private String prenom_N;
    private String numero_de_telephone_N;
    private String mail_N;


    public Nutritionniste() {
    }

    public final Nutritionniste build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResultSet(resultSet,  "nom_N", "prenom_N", "numero_de_telephone_N", "mail_N");
        return this;
    }

    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, nom_N, prenom_N, numero_de_telephone_N, mail_N);
    }

    public Nutritionniste(String nom_N, String prenom_N, String numero_de_telephone_N, String mail_N) {
        this.nom_N = nom_N;
        this.prenom_N = prenom_N;
        this.numero_de_telephone_N = numero_de_telephone_N;
        this.mail_N = mail_N;
    }

    public String getnom_N() {
        return nom_N;
    }

    @JsonProperty("nom_N")
    public void setnom_N(String nom_N) {
        this.nom_N = nom_N;
    }

    public String getprenom_N() {
        return prenom_N;
    }

    @JsonProperty("prenom_N")
    public void setprenom_N(String prenom_N) {
        this.prenom_N = prenom_N;
    }

    public String getnumero_de_telephone_N() {
        return numero_de_telephone_N;
    }

    @JsonProperty("numero_de_telephone_N")
    public void setnumero_de_telephone_N(String numero_de_telephone_N) {
        this.numero_de_telephone_N = numero_de_telephone_N;
    }

    public String getmail_N() {
        return mail_N;
    }

    @JsonProperty("mail_N")
    public void setmail_N(String mail_N) {
        this.mail_N = mail_N;
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
                "nom_N='" + nom_N + '\'' +
                ", prenom_N=" + prenom_N +
                ", numero_de_telephone_N='" + numero_de_telephone_N + '\'' +
                ", mail_N='" + mail_N + '\'' +
                '}';
    }
}
