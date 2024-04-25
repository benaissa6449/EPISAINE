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
    private Integer id_info;
    private Integer id_client;
    private String but;
    private String allergie;
    private Integer nbDeRepas;

    public Information() {
    }
    public final Information build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet,"id_info","id_client","but", "allergie", "nbDeRepas");
        return this;
    }
    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement,id_info, id_client, but, allergie, nbDeRepas);
    }
    
    public Information(Integer id_info, Integer id_client, String but, String allergie, Integer nbDeRepas) {
        this.id_info = id_info;
        this.id_client = id_client;
        this.but = but;
        this.allergie = allergie;
        this.nbDeRepas = nbDeRepas;
    }
    public Integer getId_info() {
        return id_info;
    }
    @JsonProperty("Id_info")
    public void setId_info(Integer id_info) {
        this.id_info = id_info;
    }
    public Integer getId_Client() {
        return id_client;
    }
    @JsonProperty("Id_client")
    public void setId_Client(Integer id_client) {
        this.id_client = id_client;
    }
    public String getBut() {
        return but;
    }
    @JsonProperty("But")
    public void setBut(String but) {
        this.but = but;
    }
    public String getAllergie() {
        return allergie;
    }
    @JsonProperty("Allergie")
    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }
    public Integer getNbDeRepas() {
        return nbDeRepas;
    }
    @JsonProperty("NbDeRepas")
    public void setNbDeRepas(Integer nbDeRepas) {
        this.nbDeRepas = nbDeRepas;
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
        return "Information{" +
                "ID_Info='" + id_info + '\'' +
                ", ID_Clients='" + id_client + '\'' +
                ", But='" + but + '\'' +
                ", Allergie='" + allergie + '\'' +
                ", Nombre de repas='" + nbDeRepas +
                '}';
    }
}
