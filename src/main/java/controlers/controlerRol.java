package controlers;

import models.RolUsuario;
import repositories.repositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class controlerRol {
    @Autowired
    private repositorioRol miRepositorioRol;


    @GetMapping("")
    public List<RolUsuario> index(){
        return this.miRepositorioRol.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RolUsuario create(@RequestBody  RolUsuario infoRol){
        return this.miRepositorioRol.save(infoRol);
    }
    @GetMapping("{id}")
    public RolUsuario show(@PathVariable String id){
        RolUsuario rolActual=this.miRepositorioRol
                        .findById(id)
                        .orElse(null);
        return rolActual;
    }
    @PutMapping("{id}")
    public RolUsuario update(@PathVariable String id,@RequestBody  RolUsuario infoRol){
        RolUsuario rolActual=this.miRepositorioRol
                .findById(id)
                .orElse(null);
        if (rolActual!=null){
            rolActual.setNombre(infoRol.getNombre());
            return this.miRepositorioRol.save(rolActual);
        }else{
            return  null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        RolUsuario rolActual=this.miRepositorioRol
                .findById(id)
                .orElse(null);
        if (rolActual!=null){
            this.miRepositorioRol.delete(rolActual);
        }
    }
}