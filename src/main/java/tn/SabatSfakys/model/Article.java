package tn.SabatSfakys.model;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity  

@Table 

public class Article {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column (unique = true)
private String ref;
@Column 
private String name;

@Column(length = 1000)
private String description;
@Column 
private int qte;
@Column 
private double prixFournisseur;
@Column 
private double prixClient;

@Column 
private Couleur couleur;

@Column 
private Genre genre;
@Column 
private Tissu tissu;
@ManyToOne
@JoinColumn(name = "idcategory" ,referencedColumnName="id") 
private Category category;

@OneToMany(mappedBy = "article", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
private List<Photo> photos = new ArrayList<>();

 @JsonIgnore // Empêche la boucle infinie en JSON
 @ManyToMany(mappedBy = "articles") // Relation bidirectionnelle
 private List<Pack> packs;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getQte() {
	return qte;
}

public void setQte(int qte) {
	this.qte = qte;
}



public double getPrixFournisseur() {
	return prixFournisseur;
}

public void setPrixFournisseur(double prixFournisseur) {
	this.prixFournisseur = prixFournisseur;
}

public double getPrixClient() {
	return prixClient;
}

public void setPrixClient(double prixClient) {
	this.prixClient = prixClient;
}

public List<Pack> getPacks() {
	return packs;
}

public void setPacks(List<Pack> packs) {
	this.packs = packs;
}



public List<Photo> getPhotos() {
	return photos;
}

public void setPhotos(List<Photo> photos) {
	this.photos = photos;
}



public String getRef() {
	return ref;
}

public void setRef(String ref) {
	this.ref = ref;
}

public Couleur getCouleur() {
	return couleur;
}

public void setCouleur(Couleur couleur) {
	this.couleur = couleur;
}

public Genre getGenre() {
	return genre;
}

public void setGenre(Genre genre) {
	this.genre = genre;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Tissu getTissu() {
	return tissu;
}

public void setTissu(Tissu tissu) {
	this.tissu = tissu;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
}



	
}
