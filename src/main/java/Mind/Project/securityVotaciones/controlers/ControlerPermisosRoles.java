package Mind.Project.securityVotaciones.controlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import Mind.Project.securityVotaciones.models.Permisos;
import Mind.Project.securityVotaciones.models.PermisosRoles;
import Mind.Project.securityVotaciones.models.RolUsuario;
import Mind.Project.securityVotaciones.repositories.repositorioPermisos;
import Mind.Project.securityVotaciones.repositories.repositorioPermisosRoles;
import Mind.Project.securityVotaciones.repositories.repositorioRol;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos-roles")
public class ControlerPermisosRoles {
    @Autowired
    private repositorioPermisosRoles miRepositorioPermisoRoles;

    @Autowired
    private repositorioPermisos miRepositorioPermiso;

    @Autowired
    private repositorioRol miRepositorioRol;


    @GetMapping("")
    public List<PermisosRoles> index(){
        return this.miRepositorioPermisoRoles.findAll();
    }

    /**
     * Asignación rol y permiso
     * @param id_rol
     * @param id_permiso
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles create(@PathVariable String id_rol,@PathVariable String id_permiso){
        PermisosRoles nuevo=new PermisosRoles();
        RolUsuario elRol=this.miRepositorioRol.findById(id_rol).get();
        Permisos elPermiso=this.miRepositorioPermiso.findById(id_permiso).get();
        if (elRol!=null && elPermiso!=null){
            nuevo.setPermiso(elPermiso);
            nuevo.setRol(elRol);
            return this.miRepositorioPermisoRoles.save(nuevo);
        }else{
            return null;
        }
    }
    @GetMapping("{id}")
    public PermisosRoles show(@PathVariable String id){
        PermisosRoles permisosRolesActual=this.miRepositorioPermisoRoles
                .findById(id)
                .orElse(null);
        return permisosRolesActual;
    }

    /**
     * Modificación Rol y Permiso
     * @param id
     * @param id_rol
     * @param id_permiso
     * @return
     */
    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles update(@PathVariable String id,@PathVariable String id_rol,@PathVariable String id_permiso){
        PermisosRoles permisosRolesActual=this.miRepositorioPermisoRoles
                .findById(id)
                .orElse(null);
        RolUsuario elRol=this.miRepositorioRol.findById(id_rol).get();
        Permisos elPermiso=this.miRepositorioPermiso.findById(id_permiso).get();
        if(permisosRolesActual!=null && elPermiso!=null && elRol!=null){
            permisosRolesActual.setPermiso(elPermiso);
            permisosRolesActual.setRol(elRol);
            return this.miRepositorioPermisoRoles.save(permisosRolesActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PermisosRoles permisosRolesActual=this.miRepositorioPermisoRoles
                .findById(id)
                .orElse(null);
        if (permisosRolesActual!=null){
            this.miRepositorioPermisoRoles.delete(permisosRolesActual);
        }
    }

    @GetMapping("validar-permiso/rol/{id_rol}")
    public PermisosRoles getPermiso(@PathVariable String id_rol,@RequestBody Permisos infoPermiso){
        Permisos elPermiso=this.miRepositorioPermiso.getPermiso(infoPermiso.getURL(),infoPermiso.getMetodo());
        RolUsuario elRol=this.miRepositorioRol.findById(id_rol).get();
        if (elPermiso!=null && elRol!=null){
            return this.miRepositorioPermisoRoles.getPermisoRol(elRol.get_id(),elPermiso.get_id());
        }else{
            return null;
        }
    }
}
