package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonRootName(value = "recette")
public class Recette {
    private int id_nutritionniste;
    private String nom_Recette;
    private int nombre_de_Calories;
    private String ingredients;
    private String instructions;
    private String regimeAlimentaire;

    public Recette() {
    }

    public final Recette build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResultSet(resultSet,  "nom_Recette", "nombre_de_Calories", "ingredients", "instructions", "regimeAlimentaire");
        return this;
    }

    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, nom_Recette, nombre_de_Calories, ingredients, instructions, regimeAlimentaire);
    }

    public Recette(int id_nutritionniste, String nom_Recette, int nombre_de_Calories, String ingredients, String instructions, String regimeAlimentaire) {
        this.nom_Recette = nom_Recette;
        this.nombre_de_Calories = nombre_de_Calories;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.regimeAlimentaire = regimeAlimentaire;
        this.id_nutritionniste = id_nutritionniste;
    }

    @JsonProperty("id_nutritionniste")
    public void setId_nutritionniste(int id_nutritionniste) {
        this.id_nutritionniste = id_nutritionniste;
    }
    public int getId_nutritionniste() {
        return id_nutritionniste;
    }
    public String getNom_Recette() {
        return nom_Recette;
    }

    @JsonProperty("nom_Recette")
    public void setNom_Recette(String nom_Recette) {
        this.nom_Recette = nom_Recette;
    }

    public int getNombre_de_Calories() {
        return nombre_de_Calories;
    }

    @JsonProperty("nombre_de_Calories")
    public void setNombre_de_Calories(int nombre_de_Calories) {
        this.nombre_de_Calories = nombre_de_Calories;
    }

    public String getIngredients() {
        return ingredients;
    }

    @JsonProperty("ingredients")
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    @JsonProperty("instructions")
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    @JsonProperty("regimeAlimentaire")
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
                "id_nutritionniste='" + id_nutritionniste + '\'' +
                ", nom_Recette='" + nom_Recette + '\'' +
                ", nombre_de_Calories=" + nombre_de_Calories +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                ", regimeAlimentaire='" + regimeAlimentaire + '\'' +
                '}';
    }
}
