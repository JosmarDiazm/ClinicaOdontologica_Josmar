package BackEndC3.ClinicaOdontologica;

import BackEndC3.ClinicaOdontologica.Entity.Domicilio;
import BackEndC3.ClinicaOdontologica.Entity.Odontologo;
import BackEndC3.ClinicaOdontologica.Entity.Paciente;
import BackEndC3.ClinicaOdontologica.Entity.Turno;
import BackEndC3.ClinicaOdontologica.Service.OdontologoService;
import BackEndC3.ClinicaOdontologica.Service.PacienteService;
import BackEndC3.ClinicaOdontologica.Service.TurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnosIntegracionTest {

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MockMvc mockMvc;

    private void cargarDatos() {
        Odontologo odontologo = new Odontologo("12345678","Jorgito", "Pereyra" );
        Odontologo odontologo2 = new Odontologo("87654321","Lionel", "Mesias" );
        odontologoService.registrarOdontologo(odontologo);
        odontologoService.registrarOdontologo(odontologo2);

        Paciente paciente = new Paciente("Jozmar", "Perez", "12345678",
                LocalDate.of(1990, 10, 10),
                new Domicilio("Calle Falsa 123", 123, "Springfield", "retiro"),
                "jozmar@jozmar");

        Paciente paciente2 = new Paciente("Jesus", "Garcia", "87654321",
                LocalDate.of(1980, 11, 13),
                new Domicilio("Calle Falsa 321", 321, "Spring", "cartoon"),
                "jesus@jesus");

        pacienteService.guardarPaciente(paciente);
        pacienteService.guardarPaciente(paciente2);

        Turno turno = new Turno(paciente, odontologo, LocalDate.now());
        turnoService.resgistrarTurno(turno);
    }

    @Test
    @Order(1)
    public void listarTurnos() throws Exception {
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(2)
    public void guardarTurnos() throws Exception{
        String json = "{\n" +
                "    \"paciente\": {\n" +
                "        \"id\": 2\n" +
                "    },\n" +
                "    \"odontologo\": {\n" +
                "        \"id\": 2\n" +
                "    },\n" +
                "    \"fecha\": \"2021-10-10\"\n" +
                "}";

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(3)
    public void eliminarTurno() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(4)
    public void actualizarTurno() throws Exception {
        String json = "{\n" +
                "    \"id\": 1,\n" +
                "    \"paciente\": {\n" +
                "        \"id\": 2\n" +
                "    },\n" +
                "    \"odontologo\": {\n" +
                "        \"id\": 2\n" +
                "    },\n" +
                "    \"fecha\": \"2021-10-10\"\n" +
                "}";

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.put("/turnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(5)
    public void buscarTurnoPorId() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
