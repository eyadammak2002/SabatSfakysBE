package tn.SabatSfakys.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Stratégie pour l'héritage en base de données

public class Client extends Personne {
    	@Enumerated(EnumType.STRING)  // Assurez-vous que l'enum est stocké en String
    	@Column
	    private Genre sexe;
	    
		
		public Genre getSexe() {
			return sexe;
		}
		public void setSexe(Genre sexe) {
			this.sexe = sexe;
		}
		
		
	}


