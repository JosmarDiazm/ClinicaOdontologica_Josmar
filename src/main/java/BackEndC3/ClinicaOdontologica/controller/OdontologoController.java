package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.Entity.Odontologo;
import BackEndC3.ClinicaOdontologica.Service.OdontologoService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")

public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo eliminado");
        }else {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> buscarOdontologo = odontologoService.buscarPorId(odontologo.getId());
        if (buscarOdontologo.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo actualizado");
        }else {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
    }
    @GetMapping("/buscar")
    public ResponseEntity<Optional<Odontologo>>buscarPorMatricula(@RequestParam String matricula){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        if (odontologoBuscado.isEmpty()){
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
        return ResponseEntity.ok(odontologoBuscado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>>buscarPorId(@PathVariable Long id){
        Optional<Odontologo> odontologoPorId =odontologoService.buscarPorId(id);
        if (odontologoPorId.isEmpty()){
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
        return ResponseEntity.ok(odontologoPorId);
    }
}
