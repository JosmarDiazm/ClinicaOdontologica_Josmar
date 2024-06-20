package BackEndC3.ClinicaOdontologica.Repository;

import BackEndC3.ClinicaOdontologica.Entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    Optional<Odontologo> findByNumeroMatricula (String numeroMatricula);

}
