package ar.edu.unlam.tallerweb1.domain.usuarios.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	private String rol;
	private Boolean activo = false;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public boolean activo() {
		return activo;
    }

    public void activar() {
		activo = true;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(id, usuario.id) && Objects.equals(email, usuario.email) && Objects.equals(password, usuario.password) && Objects.equals(rol, usuario.rol) && Objects.equals(activo, usuario.activo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, password, rol, activo);
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", rol='" + rol + '\'' +
				", activo=" + activo +
				'}';
	}
}