package tn.SabatSfakys.model;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column 
    protected String username;
 
    @Column 
    protected String email;
    @Column 
    protected String adresse;
    @Column 
    protected String telephone;
    @Column 
    protected String password;

    
   

    public Personne() {
	
	}

	public Personne(int id, String username, String email, String adresse, String telephone, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.adresse = adresse;
		this.telephone = telephone;
		this.password = password;
	}
	public Personne(String username, String email, String adresse, String telephone, String password) {
		super();
		
		this.username = username;
		this.email = email;
		this.adresse = adresse;
		this.telephone = telephone;
		this.password = password;
	}
	
	public Personne(String username, String email,String password) {
	
		this.username = username;
		this.email = email;
		this.password = password;
	}

	// Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return username;
    }

    public void setNom(String username) {
        this.username = username;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

	public String getMotDePasse() {
		return password;
	}

	public void setMotDePasse(String password) {
		this.password = password;
	}
    
}


