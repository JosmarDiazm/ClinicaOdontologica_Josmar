package BackEndC3.ClinicaOdontologica.Service;

import BackEndC3.ClinicaOdontologica.Entity.Domicilio;
import BackEndC3.ClinicaOdontologica.Entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    private final Long id = 1L;

    @Test
    @Order(1)
    public void guardarPacienteTest() {
        Paciente paciente = new Paciente("Juan", "Perez", "12345678",
                LocalDate.of(1990, 10, 10),
                new Domicilio("Calle Falsa 123", 123, "Springfield", "1234"),
                "paciente@paciente.com"
        );
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        assertEquals(1L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorId() {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarPacienteTest() {
        Paciente paciente = new Paciente(id, "Jesus", "Perez", "12345678",
                LocalDate.of(1990, 10, 10),
                new Domicilio("Calle Falsa 123", 123, "Springfield", "1234"),
                "paciente@paciente");
        pacienteService.actualizarPaciente(paciente);

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        assertEquals("Jesus", pacienteBuscado.get().getNombre());

    }

    @Test
    @Order(4)
    public void buscarPacientePorEmail() {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail("paciente@paciente");
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(5)
    public void listarPacientesTest() {
        assertNotNull(pacienteService.listarTodos());
    }


    @Test
    @Order(6)
    public void eliminarPacienteTest() {
        pacienteService.eliminarPaciente(id);
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        assertEquals(Optional.empty(), pacienteBuscado);
    }

}