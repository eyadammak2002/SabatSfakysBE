package tn.SabatSfakys.model;


import jakarta.persistence.Entity;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Builder;


@Entity
@Inheritance(strategy = InheritanceType.JOINED) 

@Builder
public class Admin extends Personne {

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int id, String username, String email, String adresse, String telephone, String password) {
		super(id, username, email, adresse, telephone, password);
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String email, String adresse, String telephone, String password) {
		super(username, email, adresse, telephone, password);
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String email, String password) {
		super(username, email, password);
		// TODO Auto-generated constructor stub
	}
    // Vous pouvez ajouter des champs spécifiques aux admins ici, si nécessaire.
	
}
