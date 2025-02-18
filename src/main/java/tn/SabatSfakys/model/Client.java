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
	    @Column 
	    private String prenom;
	   
    	@Enumerated(EnumType.STRING)  // Assurez-vous que l'enum est stocké en String
    	@Column
	    private Genre sexe;
	    
    	
    	 public Client() {}

    	 public Client(String nom, String prenom, String email, String adresse, String telephone,Genre sexe ,String motDePasse) {
    	     super(nom, adresse,telephone, email, motDePasse); // Utilisation du constructeur de Personne
    	     this.sexe = sexe;
    	     this.prenom = prenom;
    	 }
		
		public Genre getSexe() {
			return sexe;
		}
		public void setSexe(Genre sexe) {
			this.sexe = sexe;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		
		
	}


