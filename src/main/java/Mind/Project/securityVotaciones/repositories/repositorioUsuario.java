package Mind.Project.securityVotaciones.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import Mind.Project.securityVotaciones.models.Usuarios;

public interface repositorioUsuario extends MongoRepository<Usuarios,String> {
    @Query("{'correo': ?0}")
    public Usuarios getUserByEmail(String correo);
}