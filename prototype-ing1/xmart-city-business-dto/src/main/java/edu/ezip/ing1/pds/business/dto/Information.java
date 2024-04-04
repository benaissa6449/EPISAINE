package edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "information")
public class Information {
    private String id_Client;
    private String but;
    private String allergie;
    private String nbDeRepas;

    public Information() {
    }
    public final Information build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet,"id_Client","but", "allergie", "nbDeRepas");
        return this;
    }
    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, id_Client, but, allergie, nbDeRepas);
    }
    
    public Information(String id_Client, String but, String allergie, String nbDeRepas) {
        this.id_Client = id_Client;
        this.but = but;
        this.allergie = allergie;
        this.nbDeRepas = nbDeRepas;
    }
    public String getId_Client() {
        return id_Client;
    }
    @JsonProperty("id_Client")
    public void setId_Client(String id_Client) {
        this.id_Client = id_Client;
    }
    public String getBut() {
        return but;
    }
    @JsonProperty("but")
    public void setBut(String but) {
        this.but = but;
    }
    public String getAllergie() {
        return allergie;
    }
    @JsonProperty("allergie")
    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }
    public String getNbDeRepas() {
        return nbDeRepas;
    }
    @JsonProperty("nbDeRepas")
    public void setNbDeRepas(String nbDeRepas) {
        this.nbDeRepas = nbDeRepas;
    }

    private void setFieldsFromResulset(final ResultSet resultSet, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for(final String fieldName : fieldNames ) {
            final Field field = this.getClass().getDeclaredField(fieldName);
            field.set(this, resultSet.getObject(fieldName).toString());
        }
    }
    
    private final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement, final String ... fieldNames)
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;
        for(final String fieldName : fieldNames) {
            preparedStatement.setString(++ix, fieldName);
        }
        return preparedStatement;
    }

    @Override
    public String toString() {
        return "Information{" +
                "ID_ Clients='" + id_Client + '\'' +
                ", But='" + but + '\'' +
                ", Allergie='" + allergie + '\'' +
                ", Nombre de repas='" + nbDeRepas +
                '}';
    }

    public String[] getValue() {
        String [] res = {id_Client, but, allergie, nbDeRepas};
        return res;
    }
}
