package tn.SabatSfakys.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre {
    Homme, Femme;

    @JsonCreator
    public static Genre fromString(String value) {
        for (Genre genre : Genre.values()) {
            if (genre.name().equalsIgnoreCase(value)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Genre invalide : " + value);
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}
