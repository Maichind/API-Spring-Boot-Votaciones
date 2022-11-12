package Mind.Project.securityVotaciones.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import Mind.Project.securityVotaciones.models.RolUsuario;
import Mind.Project.securityVotaciones.models.Usuarios;
import Mind.Project.securityVotaciones.repositories.repositorioRol;
import Mind.Project.securityVotaciones.repositories.repositorioUsuario;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class controlerUsuario {
    @Autowired
    private repositorioUsuario miRepositorioUsuario;
    @Autowired
    private repositorioRol miRepositorioRol;

    @GetMapping("")
    public List<Usuarios> index(){
        return this.miRepositorioUsuario.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuarios create(@RequestBody  Usuarios infoUsuario){
        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        return this.miRepositorioUsuario.save(infoUsuario);
    }
    @GetMapping("{id}")
    public Usuarios show(@PathVariable String id){
        Usuarios usuarioActual=this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        return usuarioActual;
    }
    @PutMapping("{id}")
    public Usuarios update(@PathVariable String id,@RequestBody  Usuarios infoUsuario){
        Usuarios usuarioActual=this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        if (usuarioActual!=null){
            usuarioActual.setSeudonimo(infoUsuario.getSeudonimo());
            usuarioActual.setCorreo(infoUsuario.getCorreo());
            usuarioActual.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
            return this.miRepositorioUsuario.save(usuarioActual);
        }else{
            return null;
        }
    }

     /**
     * Relación (1 a n) entre rol y usuario
     * @param id
     * @param id_rol
     * @return
     */
    @PutMapping("{id}/rol/{id_rol}")
    public Usuarios asignarRolAs(@PathVariable String id,@PathVariable String id_rol){
        Usuarios usuarioActual=this.miRepositorioUsuario.findById(id).orElse(null);
        RolUsuario rolActual=this.miRepositorioRol.findById(id_rol).orElse(null);
        if (usuarioActual!=null && rolActual!=null){
            usuarioActual.setRol(rolActual);
            return this.miRepositorioUsuario.save(usuarioActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Usuarios usuarioActual=this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        if (usuarioActual!=null){
            this.miRepositorioUsuario.delete(usuarioActual);
        }
    }
    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @PostMapping("/validate")
    public Usuarios validate(@RequestBody Usuarios infoUsuario, final HttpServletResponse response) throws IOException {
        Usuarios usuarioActual=this.miRepositorioUsuario.getUserByEmail(infoUsuario.getCorreo());
        if (usuarioActual!=null && usuarioActual.getContrasena().equals(convertirSHA256(infoUsuario.getContrasena()))) {
            usuarioActual.setContrasena("");
            return usuarioActual;
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

}