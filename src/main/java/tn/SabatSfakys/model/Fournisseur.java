package tn.SabatSfakys.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Stratégie pour l'héritage en base de données

@Builder
public class Fournisseur extends Personne {

	
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

    
    
    

    public Fournisseur( Statut statut, String numeroIdentificationEntreprise,Photo logo, String materiauxUtilises,
			String methodesProduction, String programmeRecyclage, String transportLogistiqueVerte,
			String initiativesSociales, double scoreEcologique) {
    	
		super();
		this.logo = logo;
		this.statut = statut;
		this.numeroIdentificationEntreprise = numeroIdentificationEntreprise;
		this.materiauxUtilises = materiauxUtilises;
		this.methodesProduction = methodesProduction;
		this.programmeRecyclage = programmeRecyclage;
		this.transportLogistiqueVerte = transportLogistiqueVerte;
		this.initiativesSociales = initiativesSociales;
		this.scoreEcologique = scoreEcologique;
	}
    
	public Fournisseur(String username, String email, String adresse, String telephone, String password,Photo logo,
			 Statut statut,String numeroIdentificationEntreprise,String materiauxUtilises,String methodesProduction,String programmeRecyclage,String transportLogistiqueVerte
			,String initiativesSociales,double scoreEcologique) {
		
		super( username, email, adresse, telephone, password);
		// TODO Auto-generated constructor stub
		this.logo = logo;
		this.statut = statut;
		this.numeroIdentificationEntreprise = numeroIdentificationEntreprise;
		this.materiauxUtilises = materiauxUtilises;
		this.methodesProduction = methodesProduction;
		this.programmeRecyclage = programmeRecyclage;
		this.transportLogistiqueVerte = transportLogistiqueVerte;
		this.initiativesSociales = initiativesSociales;
		this.scoreEcologique = scoreEcologique;
	}
	
	

	public Fournisseur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fournisseur(int id, String username, String email, String adresse, String telephone, String password) {
		super(id, username, email, adresse, telephone, password);
		// TODO Auto-generated constructor stub
	}

	public Fournisseur(String username, String email, String adresse, String telephone, String password) {
		super(username, email, adresse, telephone, password);
		// TODO Auto-generated constructor stub
	}

	public Fournisseur(String username, String email, String password) {
		super(username, email, password);
		// TODO Auto-generated constructor stub
	}

	// Getters et Setters
    public String getNumeroIdentificationEntreprise() {
        return numeroIdentificationEntreprise;
    }

    public void setNumeroIdentificationEntreprise(String numeroIdentificationEntreprise) {
        this.numeroIdentificationEntreprise = numeroIdentificationEntreprise;
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

	public void setTransportLogistiqueVerte(String transportLogistiqueVerte) {
		this.transportLogistiqueVerte = transportLogistiqueVerte;
	}

}

