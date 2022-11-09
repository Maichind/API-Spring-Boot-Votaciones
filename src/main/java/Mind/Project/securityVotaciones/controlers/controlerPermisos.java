package Mind.Project.securityVotaciones.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import Mind.Project.securityVotaciones.models.Permisos;
import Mind.Project.securityVotaciones.repositories.repositorioPermisos;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos")
public class controlerPermisos {
    @Autowired
    private repositorioPermisos miRepositorioPermiso;

    @GetMapping("")
    public List<Permisos> index(){
        return this.miRepositorioPermiso.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permisos create(@RequestBody  Permisos infoPermiso){
        return this.miRepositorioPermiso.save(infoPermiso);
    }
    @GetMapping("{id}")
    public Permisos show(@PathVariable String id){
        Permisos permisoActual=this.miRepositorioPermiso
                .findById(id)
                .orElse(null);
        return permisoActual;
    }
    @PutMapping("{id}")
    public Permisos update(@PathVariable String id,@RequestBody  Permisos infoPermiso){
        Permisos permisoActual=this.miRepositorioPermiso
                .findById(id)
                .orElse(null);
        if(permisoActual!=null){
            permisoActual.setMetodo(infoPermiso.getMetodo());
            permisoActual.setURL(infoPermiso.getURL());
            return this.miRepositorioPermiso.save(permisoActual);
        }else{
            return null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Permisos permisoActual=this.miRepositorioPermiso
                .findById(id)
                .orElse(null);
        if (permisoActual!=null){
            this.miRepositorioPermiso.delete(permisoActual);
        }
    }
}
