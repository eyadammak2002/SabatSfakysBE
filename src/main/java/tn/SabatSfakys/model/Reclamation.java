package tn.SabatSfakys.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity  

@Table 

public class Reclamation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column 
	private long id;  
    @Column(length = 2000) 
	private String description;
	@Column  
    private boolean resoudre = false; 

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isResoudre() {
		return resoudre;
	}
	public void setResoudre(boolean resoudre) {
		this.resoudre = resoudre;
	}
	

	
}
