package Mind.Project.securityVotaciones.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PermisosRoles {
    @Id
    private String _id;
    @DBRef
    private RolUsuario rol;
    @DBRef
    private Permisos permiso;

    public PermisosRoles() {
    }

    public String get_id() {
        return _id;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public Permisos getPermiso() {
        return permiso;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public void setPermiso(Permisos permiso) {
        this.permiso = permiso;
    }
}
