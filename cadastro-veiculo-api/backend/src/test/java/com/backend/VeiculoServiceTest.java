package com.backend;

import com.backend.backend.domain.Veiculo;
import com.backend.backend.repository.VeiculoRepository;
import com.backend.backend.service.VeiculoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class VeiculoServiceTest {
    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveListarVeiculos() {
        Veiculo veiculo1 = new Veiculo(1L, "Celta", "Chevrolet", 2010, "Carro popular", false, LocalDate.now(), null, "Prata");
        Veiculo veiculo2 = new Veiculo(2L, "Uno", "Fiat", 2005, "Carro popular", true, LocalDate.now(), null, "Vermelho");

        List<Veiculo> veiculos = Arrays.asList(veiculo1, veiculo2);

        when(veiculoRepository.findAll()).thenReturn(veiculos);

        List<Veiculo> result = veiculoService.listarVeiculos();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(veiculo1, result.get(0));
        Assertions.assertEquals(veiculo2, result.get(1));
    }

    @Test
    void deveEncontrarVeiculosPorMarcaAnoCor() {
        Veiculo veiculo1 = new Veiculo(1L, "Celta", "Chevrolet", 2010, "Carro popular", false, LocalDate.now(), null, "Prata");
        Veiculo veiculo2 = new Veiculo(2L, "Uno", "Fiat", 2005, "Carro popular", true, LocalDate.now(), null, "Vermelho");

        List<Veiculo> veiculos = Arrays.asList(veiculo1, veiculo2);

        when(veiculoRepository.findAll()).thenReturn(veiculos);

        List<Veiculo> result = veiculoService.encontrarVeiculosPorMarcaAnoCor("Chevrolet", 2010, "Prata");

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(veiculo1, result.get(0));
    }

    @Test
    void deveEncontrarVeiculoPorId() {
        Veiculo veiculo = new Veiculo(1L, "Celta", "Chevrolet", 2010, "Carro popular", false, LocalDate.now(), null, "Prata");

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        Veiculo result = veiculoService.encontrarVeiculoPorId(1L);

        Assertions.assertEquals(veiculo, result);
    }

    @Test
    void deveAdicionarVeiculo() {
        Veiculo veiculo = new Veiculo(1L, "Celta", "Chevrolet", 2010, "Carro popular", false, LocalDate.now(), null, "Prata");

        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        Veiculo result = veiculoService.adicionarVeiculo(veiculo);

        Assertions.assertEquals(veiculo, result);
    }

    @Test
    void deveAtualizarVeiculo() {
        Veiculo veiculo = new Veiculo(1L, "Fusca", "Volkswagen", 1960, "Veículo clássico", false, LocalDate.now(), LocalDate.now(), "Azul");
        Veiculo veiculoAtualizado = new Veiculo(1L, "Fusca", "Volkswagen", 1961, "Veículo clássico", false, LocalDate.now(), LocalDate.now(), "Azul");
        Mockito.when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
    }
}
