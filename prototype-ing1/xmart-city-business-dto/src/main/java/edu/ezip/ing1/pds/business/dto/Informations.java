package edu.ezip.ing1.pds.business.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Informations {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("informations")
    private Set<Information> informations = new LinkedHashSet<Information>();

    public Set<Information> getInformations() {
        return informations;
    }

    @JsonProperty("informations")
    public void setInformations(Set<Information> informations) {
        this.informations = informations;
    }

    public final Informations add (final Information information) {
        informations.add(information);
        return this;
    }

    @Override
    public String toString() {
        return "Informations{" +
                "informations=" + informations +
                '}';
    }
}
