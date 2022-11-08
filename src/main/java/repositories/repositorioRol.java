package repositories;

import models.RolUsuario; 
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repositorioRol extends MongoRepository<RolUsuario,String>{
    
}
