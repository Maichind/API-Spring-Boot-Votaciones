package repositories;

import models.Usuarios; 
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repositorioUsuario extends MongoRepository<Usuarios,String> {
    
}
