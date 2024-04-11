package edu.ezip.ing1.pds.business.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class Nutritionnistes {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nutritionnistes")
    private Set<Nutritionniste> nutritionnistes = new LinkedHashSet<>();

    public Set<Nutritionniste> getNutritionnistes() {
        return nutritionnistes;
    }

    @JsonProperty("nutritionnistes")
    public void setNutritionnistes(Set<Nutritionniste> nutritionnistes) {
        this.nutritionnistes = nutritionnistes;
    }

    public final Nutritionnistes add(final Nutritionniste nutritionniste) {
        nutritionnistes.add(nutritionniste);
        return this;
    }

    @Override
    public String toString() {
        return "Nutritionnistes{" +
                "nutritionnistes=" + nutritionnistes +
                '}';
    }
}
