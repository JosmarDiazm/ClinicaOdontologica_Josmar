package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.Entity.Paciente;
import BackEndC3.ClinicaOdontologica.Service.PacienteService;

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
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){

        Optional<Paciente> BuscarPaciente = pacienteService.buscarPorId(paciente.getId());
        if (BuscarPaciente.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado");
        }else {
            return ResponseEntity.badRequest().build();
        }
    };

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Paciente>> BuscarPaciente(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }
    @GetMapping("/buscar")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@RequestParam String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(email);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);

        }else {
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado");
        }else {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }


}
