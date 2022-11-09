package Mind.Project.securityVotaciones.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Mind.Project.securityVotaciones.models.Permisos;

public interface repositorioPermisos extends MongoRepository<Permisos,String>{
    
}
