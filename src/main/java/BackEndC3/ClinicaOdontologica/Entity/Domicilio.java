package BackEndC3.ClinicaOdontologica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="domicilios")

public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String Calle;
    @Column
    private Integer Numero;
    @Column
    private String Localidad;
    @Column
    private String Provincia;

    public Domicilio(String calle, Integer numero, String localidada, String provincia) {
        this.Calle = calle;
        this.Numero = numero;
        this.Localidad = localidada;
        this.Provincia = provincia;
    }

    public Domicilio(Long id, String calle, Integer numero, String localidada, String provincia) {
        this.id = id;
        this.Calle = calle;
        this.Numero = numero;
        this.Localidad = localidada;
        this.Provincia = provincia;
    }

    public Domicilio() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCalle() {
        return Calle;
    }

    public Integer getNumero() {
        return Numero;
    }

    public String getLocalidad() {
        return Localidad;
    }


    public String getProvincia() {
        return Provincia;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", Calle='" + Calle + '\'' +
                ", Numero=" + Numero +
                ", Localidada='" + Localidad + '\'' +
                ", Provincia='" + Provincia + '\'' +
                '}';
    }
}
