package BackEndC3.ClinicaOdontologica.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PacienteNotFoundException extends RuntimeException{

    private Long id;
    private String email;

    public PacienteNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }
}
