package Mind.Project.securityVotaciones.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Mind.Project.securityVotaciones.models.Usuarios;

public interface repositorioUsuario extends MongoRepository<Usuarios,String> {
    
}
