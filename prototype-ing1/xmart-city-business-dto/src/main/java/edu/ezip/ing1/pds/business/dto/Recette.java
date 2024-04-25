package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "recette")
public class Recette {
    private Integer id_recette;
    private Integer id_nutritionniste;
    private String nom_recette;
    private Integer nombre_de_calories;
    private String ingredients;
    private String instructions;
    private String regimeAlimentaire;

    public Recette() {
    }

    public final Recette build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResultSet(resultSet,"id_recette",  "nom_recette", "nombre_de_calories", "ingredients", "instructions", "regimeAlimentaire", "id_nutritionniste");
        return this;
    }

    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, id_recette, nom_recette, nombre_de_calories, ingredients, instructions, regimeAlimentaire);
    }

    public Recette(Integer id_recette, Integer id_nutritionniste, String nom_recette, Integer nombre_de_calories, String ingredients, String instructions, String regimeAlimentaire) {
        this.id_recette = id_recette;
        this.nom_recette = nom_recette;
        this.nombre_de_calories = nombre_de_calories;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.regimeAlimentaire = regimeAlimentaire;
        this.id_nutritionniste = id_nutritionniste;
    }

    public Integer getId_recette() {
        return id_recette;
    }

    public void setId_recette(Integer id_recette) {
        this.id_recette = id_recette;
    }

    @JsonProperty("Nom_recette")
    public void setNom_recette(String nom_recette) {
        this.nom_recette = nom_recette;
    }
    public String getNom_recette() {
        return nom_recette;
    }


    @JsonProperty("Id_nutritionniste")
    public void setId_nutritionniste(Integer id_nutritionniste) {
        this.id_nutritionniste = id_nutritionniste;
    }
    public Integer getId_nutritionniste() {
        return id_nutritionniste;
    }


    @JsonProperty("Nombre_de_calories")
    public void setNombre_de_calories(Integer nombre_de_calories) {
        this.nombre_de_calories = nombre_de_calories;
    }
    public Integer getNombre_de_calories() {
        return nombre_de_calories;
    }

    public String getIngredients() {
        return ingredients;
    }

    @JsonProperty("Ingredients")
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    @JsonProperty("Instructions")
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    @JsonProperty("RegimeAlimentaire")
    public void setRegimeAlimentaire(String regimeAlimentaire) {
        this.regimeAlimentaire = regimeAlimentaire;
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
        return "Recette{" +
                "Id_nutritionniste='" + id_nutritionniste + '\'' +
                ", Nom_Recette='" + nom_recette + '\'' +
                ", Nombre_de_calories=" + nombre_de_calories +
                ", Ingredients='" + ingredients + '\'' +
                ", Instructions='" + instructions + '\'' +
                ", RegimeAlimentaire='" + regimeAlimentaire + '\'' +
                '}';
    }
}
