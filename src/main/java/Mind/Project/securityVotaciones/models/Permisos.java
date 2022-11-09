package Mind.Project.securityVotaciones.models;

import lombok.Data; 
import org.springframework.data.annotation.Id; 
import org.springframework.data.mongodb.core.mapping.Document; 

@Data 
@Document() 
public class Permisos {
    @Id 
    private String _id; 
    private String url; 
    private String metodo;

    public Permisos(String url, String metodo) {
        this.url = url;
        this.metodo = metodo;
    }

    public String get_id() { 
        return _id; 
    } 
    public String getURL() { 
        return url; 
    } 
    public void setURL(String url) { 
        this.url = url; 
    } 
    public String getMetodo() { 
        return metodo; 
    } 
    public void setMetodo(String metodo) { 
        this.metodo = metodo; 
    }
}
