package tn.SabatSfakys.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import tn.SabatSfakys.model.ERole;
import tn.SabatSfakys.model.Genre;
import tn.SabatSfakys.model.Photo;
import tn.SabatSfakys.model.Statut;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    
 
    
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private ERole role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
  
    @Column 
    protected String adresse;
    @Column 
    protected String telephone;
   
	@Enumerated(EnumType.STRING)  // Assurez-vous que l'enum est stocké en String
	@Column
    private Genre sexe;
	
		@ManyToOne
	    @JoinColumn(name = "id_photo")
	    private Photo logo;

	    @Enumerated(EnumType.STRING)
	    private Statut statut = Statut.EN_ATTENTE; // Par défaut en attente

	    @Column
	    private String numeroIdentificationEntreprise; // SIRET, TVA, etc.

	    @Column
	    private String materiauxUtilises; // Ex: cuir végétal, coton bio

	   
	    @Column
	    private String methodesProduction; // Ex: énergie renouvelable, zéro déchet

	    @Column
	    private String programmeRecyclage; // Ex: acceptation des retours usagés

	    @Column
	    private String transportLogistiqueVerte; // Ex: transport neutre en carbone

	    @Column
	    private String initiativesSociales; // Ex: collaboration avec artisans locaux

	    @Column
	    private double scoreEcologique; // Note d’évaluation de l’engagement écologique
	    
	    
	    
	    
	    
	    
	    
	    

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

	
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public ERole getRole() {
			return role;
		}

		public void setRole(ERole role) {
			this.role = role;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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

		public Genre getSexe() {
			return sexe;
		}

		public void setSexe(Genre sexe) {
			this.sexe = sexe;
		}

		public Photo getLogo() {
			return logo;
		}

		public void setLogo(Photo logo) {
			this.logo = logo;
		}

		public Statut getStatut() {
			return statut;
		}

		public void setStatut(Statut statut) {
			this.statut = statut;
		}

		public String getNumeroIdentificationEntreprise() {
			return numeroIdentificationEntreprise;
		}

		public void setNumeroIdentificationEntreprise(String numeroIdentificationEntreprise) {
			this.numeroIdentificationEntreprise = numeroIdentificationEntreprise;
		}

		public String getMateriauxUtilises() {
			return materiauxUtilises;
		}

		public void setMateriauxUtilises(String materiauxUtilises) {
			this.materiauxUtilises = materiauxUtilises;
		}

		public String getMethodesProduction() {
			return methodesProduction;
		}

		public void setMethodesProduction(String methodesProduction) {
			this.methodesProduction = methodesProduction;
		}

		public String getProgrammeRecyclage() {
			return programmeRecyclage;
		}

		public void setProgrammeRecyclage(String programmeRecyclage) {
			this.programmeRecyclage = programmeRecyclage;
		}

		public String getTransportLogistiqueVerte() {
			return transportLogistiqueVerte;
		}

		public void setTransportLogistiqueVerte(String transportLogistiqueVerte) {
			this.transportLogistiqueVerte = transportLogistiqueVerte;
		}

		public String getInitiativesSociales() {
			return initiativesSociales;
		}

		public void setInitiativesSociales(String initiativesSociales) {
			this.initiativesSociales = initiativesSociales;
		}

		public double getScoreEcologique() {
			return scoreEcologique;
		}

		public void setScoreEcologique(double scoreEcologique) {
			this.scoreEcologique = scoreEcologique;
		}

    
  
	


    
 
}
