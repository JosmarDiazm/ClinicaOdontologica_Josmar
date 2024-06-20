package BackEndC3.ClinicaOdontologica.exceptions;


import BackEndC3.ClinicaOdontologica.Entity.ClinicaExceptionEntity;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ClinicaExceptions {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ClinicaExceptionEntity> exceptionHandler(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ClinicaExceptionEntity(e.getMessage(), "Error en los datos ingresados"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ClinicaExceptionEntity> exceptionHandler(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClinicaExceptionEntity(e.getMessage(), "El recurso no se encontro, ingrese un recurso valido"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ClinicaExceptionEntity> exceptionHandler(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(new ClinicaExceptionEntity(e.getMessage(), "Error en los datos ingresados"));
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<ClinicaExceptionEntity> exceptionHandler(PacienteNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ClinicaExceptionEntity(e.getMessage(), "El paciente con id "+ e.getId() + e.getMessage()));
    }

    @ExceptionHandler(PacienteNotFoundEmailException.class)
    public ResponseEntity<ClinicaExceptionEntity> exceptionHandler(PacienteNotFoundEmailException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ClinicaExceptionEntity(e.getMessage(), "El paciente con email "+ e.getEmail() + " no se encuentra registrado"));
    }

    @ExceptionHandler(PacienteEqualEmailException.class)
    public ResponseEntity<ClinicaExceptionEntity> exceptionHandler(PacienteEqualEmailException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(new ClinicaExceptionEntity(e.getMessage(), "El email "+ e.getEmail() + " ya se encuentra registrado"));
    }
}
