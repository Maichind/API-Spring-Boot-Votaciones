package Mind.Project.securityVotaciones.models;

import lombok.Data; 
import org.springframework.data.annotation.Id; 
import org.springframework.data.mongodb.core.mapping.Document; 

@Data 
@Document() 
public class Usuarios {
    @Id 
    private String _id; 
    private String seudonimo; 
    private String contrasena;
    private String correo;

    public Usuarios(String seudonimo, String contrasena, String correo) {
        this.seudonimo = seudonimo;
        this.contrasena = contrasena;
        this.correo = correo;
    } 

    public String get_id() { 
        return _id; 
    } 
    public String getSeudonimo() { 
        return seudonimo; 
    } 
    public void setSeudonimo(String seudonimo) { 
        this.seudonimo = seudonimo; 
    } 
    public String getCorreo() { 
        return correo; 
    } 
    public void setCorreo(String correo) { 
        this.correo = correo; 
    } 
    public String getContrasena() { 
        return contrasena; 
    } 
    public void setContrasena(String contrasena) { 
        this.contrasena = contrasena; 
    }
}
