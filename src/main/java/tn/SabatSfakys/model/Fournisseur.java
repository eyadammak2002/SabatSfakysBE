package tn.SabatSfakys.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity  

@Table 

public class Fournisseur {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
@Column 
private String nom;
@Column 
private String email;
@Column 
private String adresse;
@Column 
private String telephone;

 @ManyToOne
 @JoinColumn(name = "id_photo")
 private Photo logo;
 


public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
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

public Photo getLogo() {
	return logo;
}

public void setLogo(Photo logo) {
	this.logo = logo;
}





	
}
