package BackEndC3.ClinicaOdontologica.Entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table (name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String Nombre;
    @Column
    private String Apellido;
    @Column
    private String Cedula;
    @Column
    private LocalDate FechaIngreso;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;
    @Column (unique = true)
    private String email;



    public Paciente(Long id, String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email){
        this.id = id;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Cedula = cedula;
        this.FechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }


    public Paciente(String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Cedula = cedula;
        this.FechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public Paciente() {
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Cedula='" + Cedula + '\'' +
                ", FechaIngreso=" + FechaIngreso +
                ", domicilio=" + domicilio +
                '}';
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getCedula() {
        return Cedula;
    }

    public LocalDate getFechaIngreso() {
        return FechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

     public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        FechaIngreso = fechaIngreso;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
