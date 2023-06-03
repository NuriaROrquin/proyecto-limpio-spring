package ar.edu.unlam.tallerweb1.domain.usuarios;

import javax.persistence.*;
import java.awt.*;

@Entity
public class Lugar {
    public Lugar(long idPlace, long latitude, long longitude, TextArea description, String name) {
        this.idPlace = idPlace;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_lugar")
    private long idPlace;

    @Column(name = "latitud")
    private long latitude;

    @Column(name = "longitud")
    private long longitude;

    @Column(name = "descripcion")
    private TextArea description;

    @Column(name = "nombre")
    private String name;

    public long getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(long idPlace) {
        this.idPlace = idPlace;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public TextArea getDescription() {
        return description;
    }

    public void setDescription(TextArea description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
