package cl.ufro.dci.perfecthostapi.auth.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityMixin {

    @JsonCreator
    protected SimpleGrantedAuthorityMixin(@JsonProperty(value = "authority") String role) {
    }
}
