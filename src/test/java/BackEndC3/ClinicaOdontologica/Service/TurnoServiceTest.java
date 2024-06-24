package BackEndC3.ClinicaOdontologica.Service;

import BackEndC3.ClinicaOdontologica.Entity.Domicilio;
import BackEndC3.ClinicaOdontologica.Entity.Odontologo;
import BackEndC3.ClinicaOdontologica.Entity.Paciente;
import BackEndC3.ClinicaOdontologica.Entity.Turno;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;

    private Odontologo odontologo;
    private Paciente paciente1;
    private Paciente paciente2;

    @BeforeAll
    public void inicio(){
        odontologo = new Odontologo("12345678","Jorgito", "Pereyra" );
        odontologoService.registrarOdontologo(odontologo);

        paciente1 = new Paciente("Jozmar", "Perez", "12345678",
                LocalDate.of(1990, 10, 10),
                new Domicilio("Calle Falsa 123", 123, "Springfield", "retiro"),
                "jozmar@jozmar");

        paciente2 = new Paciente("Jesus", "Garcia", "87654321",
                LocalDate.of(1980, 11, 13),
                new Domicilio("Calle Falsa 321", 321, "Spring", "cartoon"),
                "jesus@jesus");

        pacienteService.guardarPaciente(paciente1);
        pacienteService.guardarPaciente(paciente2);
    }


    @Test
    @Order(1)
    public void guardarTurnoTest() {
        Turno turno = new Turno(paciente1, odontologo, LocalDate.now());
        Turno turnoGuardado = turnoService.resgistrarTurno(turno);
        assertEquals(1L, turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId() {
        Turno turnoBuscado = turnoService.buscarPorId(1L).get();
        assertEquals(1L, turnoBuscado.getId());
    }

    @Test
    @Order(3)
    public void listarTurnos() {
        assertEquals(1, turnoService.listarTodos().size());
    }

    @Test
    @Order(4)
    public void actualizarTurnoTest() {
        Turno turno = new Turno(1L, paciente2, odontologo, LocalDate.now());
        turnoService.actualizarTurno(turno);
        Turno turnoBuscado = turnoService.buscarPorId(1L).get();
        assertEquals("Jesus", turnoBuscado.getPaciente().getNombre());
    }

    @Test
    @Order(5)
    public void eliminarTurnoTest() {
        turnoService.eliminarTurno(1L);
        assertEquals(0, turnoService.listarTodos().size());
    }
}
