package tn.SabatSfakys.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity  

@Table 

public class Pack {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column (unique = true)
private String name;

@ManyToOne
@JoinColumn(name = "fournisseur_id", nullable = false)
private Fournisseur fournisseur;

@ManyToMany
@JoinTable(
    name = "pack_article",  // Nom de la table d'association
    joinColumns = @JoinColumn(name = "pack_id"),  // Clé étrangère vers Pack
    inverseJoinColumns = @JoinColumn(name = "article_id")  // Clé étrangère vers Article
)
private List<Article> articles;

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

public Fournisseur getFournisseur() {
	return fournisseur;
}

public void setFournisseur(Fournisseur fournisseur) {
	this.fournisseur = fournisseur;
}

public List<Article> getArticles() {
	return articles;
}

public void setArticles(List<Article> articles) {
	this.articles = articles;
}





	
}
