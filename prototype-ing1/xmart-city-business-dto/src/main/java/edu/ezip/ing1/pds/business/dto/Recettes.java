package edu.ezip.ing1.pds.business.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;




public class Recettes {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Recettes")
    private Set<Recette> recettes = new LinkedHashSet<>();

    public Set<Recette> getRecettes() {
        return recettes;
    }

    @JsonProperty("recettes")
    public void setRecettes(Set<Recette> recettes) {
        this.recettes = recettes;
    }

    public final Recettes add(final Recette recette) {
        recettes.add(recette);
        return this;
    }

    @Override
    public String toString() {
        return "Recettes{" +
                "recettes=" + recettes +
                '}';
    }
}
