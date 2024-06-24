package BackEndC3.ClinicaOdontologica;

import BackEndC3.ClinicaOdontologica.Service.OdontologoService;
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
public class OdontologoIntegracionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void registrarOdontologo() throws Exception {
        String json = "{\n" +
                "    \"numeroMatricula\": \"12345678\",\n" +
                "    \"nombre\": \"Jorgito\",\n" +
                "    \"apellido\": \"Pereyra\"\n" +
                "}";

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(2)
    public void listarOdontologos() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(3)
    public void actualizarOdontologo() throws Exception {
        String json = "{\n" +
                "    \"id\": 1,\n" +
                "    \"numeroMatricula\": \"87654321\",\n" +
                "    \"nombre\": \"Lionel\",\n" +
                "    \"apellido\": \"Mesias\"\n" +
                "}";

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.put("/odontologos")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(4)
    public void buscarOdontologoPorMatricula() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/buscar?matricula=87654321")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(5)
    public void buscarOdontologoPorId() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(6)
    public void eliminarOdontologo() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

}
