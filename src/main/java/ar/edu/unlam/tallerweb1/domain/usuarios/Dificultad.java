package ar.edu.unlam.tallerweb1.domain.usuarios;

import javax.persistence.*;

@Entity
public class Dificultad {

    public Dificultad(long idDifficulty, String description) {
        this.idDifficulty = idDifficulty;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_dificultad")
    private long idDifficulty;

    @Column(name = "descripcion")
    private String description;

    public long getIdDifficulty() {
        return idDifficulty;
    }

    public void setIdDifficulty(long idDifficulty) {
        this.idDifficulty = idDifficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
