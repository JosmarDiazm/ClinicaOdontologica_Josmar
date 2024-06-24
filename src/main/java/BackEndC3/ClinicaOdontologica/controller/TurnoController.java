package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.Entity.Odontologo;
import BackEndC3.ClinicaOdontologica.Entity.Paciente;
import BackEndC3.ClinicaOdontologica.Entity.Turno;
import BackEndC3.ClinicaOdontologica.Service.OdontologoService;
import BackEndC3.ClinicaOdontologica.Service.PacienteService;
import BackEndC3.ClinicaOdontologica.Service.TurnoService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    public TurnoController() {
        turnoService = new TurnoService();
        pacienteService = new PacienteService();
        odontologoService = new OdontologoService();
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(turnoService.resgistrarTurno(turno));
        } else {
            throw new ResourceNotFoundException("Paciente u Odontologo no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorId(@PathVariable Long id) {
        Optional<Turno> turnoPorId = turnoService.buscarPorId(id);
        if (turnoPorId.isEmpty()) {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
        return ResponseEntity.ok(turnoPorId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno Eliminado");
        } else {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        Optional<Turno> buscarTurno = turnoService.buscarPorId(turno.getId());
        if (buscarTurno.isPresent()) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado");
        } else {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }
}
