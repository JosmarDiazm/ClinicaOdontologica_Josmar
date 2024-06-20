package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.Entity.Paciente;
import BackEndC3.ClinicaOdontologica.Service.PacienteService;

import BackEndC3.ClinicaOdontologica.exceptions.PacienteNotFoundEmailException;
import BackEndC3.ClinicaOdontologica.exceptions.PacienteNotFoundException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> BuscarPaciente = pacienteService.buscarPorId(paciente.getId());
        if (BuscarPaciente.isPresent()){
            paciente.setFechaIngreso(BuscarPaciente.get().getFechaIngreso());
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado");
        }else {
            throw new PacienteNotFoundException("Paciente no encontrado", paciente.getId());
        }
    };

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Paciente>> BuscarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado);
        }else {
            throw new PacienteNotFoundException("Paciente no encontrado", id);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@RequestParam String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(email);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }else {
            throw new PacienteNotFoundEmailException("Paciente no encontrado", email);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado");
        }else {
            throw new PacienteNotFoundException("Paciente no encontrado", id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }
}
