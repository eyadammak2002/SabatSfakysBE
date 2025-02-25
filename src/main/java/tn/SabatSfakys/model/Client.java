package tn.SabatSfakys.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Builder;

@Entity

@Inheritance(strategy = InheritanceType.JOINED) // Stratégie pour l'héritage en base de données
@Builder(toBuilder = true)
public class Client extends Personne {
	    @Column 
	    private String prenom;
	   
    	@Enumerated(EnumType.STRING)  // Assurez-vous que l'enum est stocké en String
    	@Column
	    private Genre sexe;
	    
    	
  
		
		public Client(String nom, String email, String motDePasse) {
			super(nom, email, motDePasse);
			// TODO Auto-generated constructor stub
		}
		public Client() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Client(int id, String nom, String email, String adresse, String telephone, String motDePasse) {
			super(id, nom, email, adresse, telephone, motDePasse);
			// TODO Auto-generated constructor stub
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


