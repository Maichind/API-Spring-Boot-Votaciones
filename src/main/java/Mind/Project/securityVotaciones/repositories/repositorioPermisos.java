package Mind.Project.securityVotaciones.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import Mind.Project.securityVotaciones.models.Permisos;

public interface repositorioPermisos extends MongoRepository<Permisos,String>{
    @Query("{'url':?0,'metodo':?1}")
    Permisos getPermiso(String url, String metodo);
}