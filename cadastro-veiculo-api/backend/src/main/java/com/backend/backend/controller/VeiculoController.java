package com.backend.backend.controller;

import com.backend.backend.domain.Veiculo;
import com.backend.backend.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    @GetMapping(params = {"marca", "ano", "cor"})
    public List<Veiculo> getVeiculosPorMarcaAnoECor(@RequestParam(required = false) String marca,
                                                    @RequestParam(required = false) Integer ano,
                                                    @RequestParam(required = false) String cor) {
        return veiculoService.encontrarVeiculosPorMarcaAnoCor(marca, ano, cor);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Veiculo listarVeiculoPorID(@PathVariable Long id) {
        return veiculoService.encontrarVeiculoPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Veiculo adicionarVeiculo(@RequestBody Veiculo veiculo) {
        int i = 0;
        return veiculoService.adicionarVeiculo(veiculo);
    }

    @PutMapping("/{id}")
    public Veiculo atualizarVeiculo(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {
        return veiculoService.atualizarVeiculo(id, veiculo);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Veiculo atualizarVeiculoParcialmente(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        return veiculoService.atualizarVeiculoParcialmente(id, campos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletarVeiculoPorId(@PathVariable Long id) {
        veiculoService.apagarVeiculo(id);
    }

    @GetMapping("/nao-vendidos")
    public List<Veiculo> encontrarVeiculosNaoVendidos() {
        return veiculoService.encontrarVeiculosNaoVendidos();
    }

    @GetMapping("/por-decada")
    public Map<String, Veiculo> veiculosPorDecada() {
        Map<String, Veiculo> veiculosPorDecadaMap = new HashMap<>();
        List<List<Veiculo>> veiculosPorDecadaList = veiculoService.findVeiculosPorDecada();

        //verifica a lista de veiculos por década e adiciona
        for (List<Veiculo> veiculosDaDecada : veiculosPorDecadaList) {
            if (!veiculosDaDecada.isEmpty()) {
                Integer decada = veiculosDaDecada.get(0).getDecada();
                veiculosPorDecadaMap.put(decada.toString() + "s", veiculosDaDecada.get(0));
            }
        }

        return veiculosPorDecadaMap;
    }

    @GetMapping("/por-fabricante")
    public Map<String, List<Veiculo>> VeiculosPorFabricantes() {
        Map<String, List<Veiculo>> VeiculosPorFabricantesMap = new HashMap<>();
        Map<String, List<Veiculo>> VeiculosPorFabricantesList = veiculoService.encontrarVeiculosPorFabricantes();

        return VeiculosPorFabricantesList;
    }

    @GetMapping("/ultima-semana")
    public List<Veiculo> veiculosUltimaSemana() {

        List<Veiculo> veiculosUltimaSemana = veiculoService.buscarVeiculosultimaSemana();

        return veiculosUltimaSemana;
    }
}

