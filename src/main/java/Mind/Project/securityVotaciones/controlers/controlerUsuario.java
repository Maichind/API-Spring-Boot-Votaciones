package Mind.Project.securityVotaciones.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import Mind.Project.securityVotaciones.models.Usuarios;
import Mind.Project.securityVotaciones.repositories.repositorioUsuario;

import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class controlerUsuario {
    @Autowired
    private repositorioUsuario miRepositorioUsuario;

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
}