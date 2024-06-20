package BackEndC3.ClinicaOdontologica.exceptions;

import lombok.Data;

@Data
public class PacienteNotFoundEmailException extends RuntimeException{

    private String email;

    public PacienteNotFoundEmailException(String message, String email) {
        super(message);
        this.email = email;
    }
}
