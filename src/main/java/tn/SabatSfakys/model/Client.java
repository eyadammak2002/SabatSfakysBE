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
	
	   
    	@Enumerated(EnumType.STRING)  // Assurez-vous que l'enum est stocké en String
    	@Column
	    private Genre sexe;
	    
    	
  
	
		
		public Client(String username, String email, String adresse, String telephone, String password) {
			super(username, email, adresse, telephone, password);
			// TODO Auto-generated constructor stub
		}

		public Client(String username, String email, String password) {
			super(username, email, password);
			// TODO Auto-generated constructor stub
		}

		public Client() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public Client(int id, String username, String email, String adresse, String telephone, String password) {
			super(id, username, email, adresse, telephone, password);
			// TODO Auto-generated constructor stub
		}
		
		
		public Client( String username, String email, String adresse, String telephone, String password, Genre sexe) {
			super( username, email, adresse, telephone, password);
			this.sexe = sexe;
		}
		
		
		public Genre getSexe() {
			return sexe;
		}
		public void setSexe(Genre sexe) {
			this.sexe = sexe;
		}
	
		
	
	}


