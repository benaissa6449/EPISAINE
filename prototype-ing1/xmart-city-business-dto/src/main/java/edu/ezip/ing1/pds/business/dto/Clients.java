package edu.ezip.ing1.pds.business.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class Clients {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("clients")
    private Set<Client> clients = new LinkedHashSet<Client>();

    public Set<Client> getClients() {
        return clients;
    }

    @JsonProperty("clients")
    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public final Clients add (final Client client) {
        clients.add(client);
        return this;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "clients=" + clients +
                '}';
    }
}
