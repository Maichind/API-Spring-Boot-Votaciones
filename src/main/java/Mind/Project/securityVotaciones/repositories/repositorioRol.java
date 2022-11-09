package Mind.Project.securityVotaciones.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Mind.Project.securityVotaciones.models.RolUsuario;

public interface repositorioRol extends MongoRepository<RolUsuario,String>{
    
}
