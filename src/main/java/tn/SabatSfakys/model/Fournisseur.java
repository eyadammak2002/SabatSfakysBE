package tn.SabatSfakys.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Stratégie pour l'héritage en base de données


public class Fournisseur extends Personne{


 @ManyToOne
 @JoinColumn(name = "id_photo")
 private Photo logo;
 



public Photo getLogo() {
	return logo;
}

public void setLogo(Photo logo) {
	this.logo = logo;
}





	
}
