package tn.SabatSfakys.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import tn.SabatSfakys.model.Fournisseur;
import tn.SabatSfakys.repository.FournisseurRepository;

@Service
public class FournisseurService {

    @Autowired  
    FournisseurRepository fournisseurRepository;  // Le repository pour les fournisseurs
    
    @Autowired  
    EmailService emailService;  // Le service pour l'envoi des emails

    // Récupère tous les fournisseurs
    public List<Fournisseur> getAllFournisseurs() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        fournisseurRepository.findAll().forEach(fournisseur -> fournisseurs.add(fournisseur));
        return fournisseurs;
    }

    // Récupère un fournisseur spécifique par ID
    public Fournisseur getFournisseurById(int id) {
        return fournisseurRepository.findById(id).orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec ID: " + id));
    }

    // Sauvegarde ou met à jour un fournisseur
    public void saveOrUpdate(Fournisseur fournisseur) {
        fournisseurRepository.save(fournisseur);
    }

    // Mise à jour d'un fournisseur
    public Fournisseur updateFournisseur(int id, Fournisseur updatedFournisseur) throws MessagingException {
        Fournisseur fournisseurSaved = fournisseurRepository.findById(id).map(fournisseur -> {
            // Mise à jour des données
            fournisseur.setNom(updatedFournisseur.getNom());
            fournisseur.setEmail(updatedFournisseur.getEmail());
            fournisseur.setAdresse(updatedFournisseur.getAdresse());
            fournisseur.setTelephone(updatedFournisseur.getTelephone());
            fournisseur.setMotDePasse(updatedFournisseur.getMotDePasse());
            fournisseur.setStatut(updatedFournisseur.getStatut());
            return fournisseurRepository.save(fournisseur);  // Sauvegarde le fournisseur mis à jour
        }).orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec ID: " + id));

        // Envoi de l'email après mise à jour
        emailService.sendAuthenticationEmail(fournisseurSaved.getEmail(), fournisseurSaved.getNom());

        return fournisseurSaved;
    }

    // Suppression d'un fournisseur
    public void delete(int id) {
        fournisseurRepository.deleteById(id);
    }
}
