package BackEndC3.ClinicaOdontologica.Service;

import BackEndC3.ClinicaOdontologica.Entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologoTest() {
        Odontologo odontologo = new Odontologo("12345678","Juan", "Perez" );
        Odontologo odontologoGuardado = odontologoService.registrarOdontologo(odontologo);
        assertEquals(1L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorId() {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(1L);
        assertEquals(1L, odontologoBuscado.get().getId());
    }

    @Test
    @Order(3)
    public void buscarOdontologoPorMatricula() {
        Odontologo odontologoBuscado = odontologoService.buscarPorMatricula("12345678").get();
        assertEquals("12345678", odontologoBuscado.getNumeroMatricula());
    }

    @Test
    @Order(4)
    public void listarOdontologos() {
        assertEquals(1, odontologoService.listarTodos().size());
    }

    @Test
    @Order(5)
    public void actualizarOdontologoTest() {
        Odontologo odontologo = new Odontologo(1L, "12345678","Jesus", "Perez" );
        odontologoService.actualizarOdontologo(odontologo);

        Odontologo odontologoBuscado = odontologoService.buscarPorId(1L).get();
        assertEquals("Jesus", odontologoBuscado.getNombre());
    }

    @Test
    @Order(6)
    public void eliminarOdontologoTest() {
        odontologoService.eliminarOdontologo(1L);
        assertEquals(0, odontologoService.listarTodos().size());
    }

}
