package tn.SabatSfakys.repository;

import org.springframework.data.repository.CrudRepository;

import tn.SabatSfakys.model.Client;


public interface ClientRepository extends CrudRepository<Client, Integer> {
}
