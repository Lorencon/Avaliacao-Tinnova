package com.backend;

import com.backend.backend.domain.Veiculo;
import com.backend.backend.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@ContextConfiguration(classes=Veiculo.class)
@DataJpaTest
@SpringBootTest(classes = Veiculo.class)
@SpringJUnitWebConfig
@ActiveProfiles("/api/veiculos-test")
public class VeiculoControllerTest {
    private final String URI_VEICULOS = "/veiculos";
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private VeiculoService veiculoService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void listarVeiculos_retornaListaDeVeiculos() throws Exception {
        List<Veiculo> veiculos = Arrays.asList(
                new Veiculo(1L, "Gol", "Volkswagen", 2020, "Novo Gol", false, LocalDate.now(), null, "Preto"),
                new Veiculo(2L, "Onix", "Chevrolet", 2015, "Chevrolet em ótimo estado", true, LocalDate.now(), null, "Branco")
        );
        Mockito.when(veiculoService.listarVeiculos()).thenReturn(veiculos);

        mockMvc.perform(MockMvcRequestBuilders.get(URI_VEICULOS)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].veiculo").value("Gol"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].marca").value("Volkswagen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ano").value(2020))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].descricao").value("Novo Gol"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vendido").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cor").value("Preto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].veiculo").value("Onix"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].marca").value("Chevrolet"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].ano").value(2015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].descricao").value("Chevrolet em ótimo estado"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].vendido").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cor").value("Branco"));
    }

    @Test
    public void adicionarVeiculo_retornaVeiculoAdicionado() throws Exception {
        Veiculo veiculo = new Veiculo(null, "Corolla", "Toyota", 2022, "Novo Corolla", false, LocalDate.now(), null, "Prata");
        Mockito.when(veiculoService.adicionarVeiculo(Mockito.any(Veiculo.class))).thenThrow(new Exception("Erro ao adicionar veículo"));

        String jsonRequest = "{\n" +
                "    \"veiculo\": \"Corolla\",\n" +
                "    \"marca\": \"Toyota\",\n" +
                "    \"ano\": 2022,\n" +
                "    \"descricao\": \"Novo Corolla\",\n" +
                "    \"vendido\": false,\n" +
                "    \"cor\": \"Prata\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post(URI_VEICULOS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Erro ao adicionar veículo"));
    }

//    @Test
//    public void atualizarVeiculo_retornaVeiculoAtualizado() throws Exception {
//        Veiculo veiculoAtualizado = new Veiculo(1L, "Corolla", "Toyota", 2022, "Corolla atualizado", true, LocalDate.now(), null, "Prata");
//        Mockito.when(veiculoService.atualizarVeiculo(Mockito.anyLong(), Mockito.any(Veiculo.class))).thenReturn(veiculoAtualizado);
//
//        String veiculoAtualizadoJson = "{\"veiculo\": \"Gol Novo\", \"marca\": \"Volkswagen\", \"ano\": 2021, \"descricao\": \"Novo Gol com motor turbo\", \"vendido\": true, \"cor\": \"Prata\"}";
//        mockMvc.perform(MockMvcRequestBuilders.put(URI_VEICULOS + "/{id}", idVeiculo)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(veiculoAtualizadoJson))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idVeiculo))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.veiculo").value("Gol Novo"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.marca").value("Volkswagen"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value(2021))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value("Novo Gol com motor turbo"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.vendido").value(true))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.cor").value("Prata"));
//    }

//    @Test
//    public void excluirVeiculo_retornaVeiculoExcluido() throws Exception {
//        Long idVeiculo = 1L;
//        Mockito.when(veiculoService.excluirVeiculo(Mockito.anyLong())).thenReturn(true);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete(URI_VEICULOS + "/{id}", idVeiculo))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("true"));
//    }
//
//    @Test
//    public void buscarVeiculoPorId_retornaVeiculo() throws Exception {
//        Long idVeiculo = 1L;
//        Veiculo veiculo = new Veiculo(idVeiculo, "Gol", "Volkswagen", 2020, "Novo Gol", false, LocalDate.now(), null, "Preto");
//        Mockito.when(veiculoService.buscarVeiculoPorId(Mockito.anyLong())).thenReturn(veiculo);
//
//        mockMvc.perform(MockMvcRequestBuilders.get(URI_VEICULOS + "/{id}", idVeiculo)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idVeiculo))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.veiculo").value("Gol"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.marca").value("Volkswagen"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value(2020))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value("Novo Gol"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.vendido").value(false))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.cor").value("Preto"));
//    }






    }
