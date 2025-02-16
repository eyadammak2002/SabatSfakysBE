package tn.SabatSfakys.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tn.SabatSfakys.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Integer>{
	 void deleteByName(String name);
	    
	    Optional<Photo> findByName(String name);
	    List<Photo> findAllById(Iterable<Integer> ids);



}
 