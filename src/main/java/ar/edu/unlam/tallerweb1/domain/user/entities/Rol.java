package ar.edu.unlam.tallerweb1.domain.user.entities;

import javax.persistence.*;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rol")
    private long idRole;

    @Column(name = "descripcion")
    private String description;

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
