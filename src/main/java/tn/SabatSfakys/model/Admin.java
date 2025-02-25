package tn.SabatSfakys.model;


import jakarta.persistence.Entity;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Builder;


@Entity
@Inheritance(strategy = InheritanceType.JOINED) 

@Builder
public class Admin extends Personne {
    // Vous pouvez ajouter des champs spécifiques aux admins ici, si nécessaire.
}
