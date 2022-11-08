package repositories;

import models.Permisos; 
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repositorioPermisos extends MongoRepository<Permisos,String>{
    
}
