package tn.SabatSfakys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.SabatSfakys.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}
