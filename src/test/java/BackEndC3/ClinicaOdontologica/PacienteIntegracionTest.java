package BackEndC3.ClinicaOdontologica;

import BackEndC3.ClinicaOdontologica.Service.PacienteService;
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

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteIntegracionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void registrarPaciente() throws Exception {
        String json = "{\n" +
                "    \"nombre\": \"Jozmar\",\n" +
                "    \"apellido\": \"Diaz\",\n" +
                "    \"cedula\": \"12345678\",\n" +
                "    \"fechaIngreso\": \"2021-09-01\",\n" +
                "    \"domicilio\": {" +
                "       \"calle\": \"Siempre viva\",\n" +
                "       \"numero\": 123,\n" +
                "       \"localidad\": \"CABA\",\n" +
                "       \"provincia\" : \"Lima\"\n" +
                "    }," +
                "   \"email\":\"jozmar@jozmar\"" +
                "}";

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/paciente")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(2)
    public void listarPacientes() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/paciente")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(3)
    public void actualizarPaciente() throws Exception {
        String json = "{\n" +
                "    \"id\": 1,\n" +
                "    \"nombre\": \"Jesus\",\n" +
                "    \"apellido\": \"Garcia\",\n" +
                "    \"cedula\": \"12345678\",\n" +
                "    \"fechaIngreso\": \"2021-09-01\",\n" +
                "    \"domicilio\": {" +
                "       \"calle\": \"Siempre viva\",\n" +
                "       \"numero\": 123,\n" +
                "       \"localidad\": \"CABA\",\n" +
                "       \"provincia\" : \"Lima\"\n" +
                "    }," +
                "   \"email\":\"jozmar@jozmar\"" +
                "}";

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.put("/paciente/actualizar")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(4)
    public void buscarPacientePorId() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/paciente/buscar/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(5)
    public void buscarPacientePorEmail() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/paciente/buscar?email=jozmar@jozmar")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(6)
    public void eliminarPaciente() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.delete("/paciente/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }


}
