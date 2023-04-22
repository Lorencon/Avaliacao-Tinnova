package com.backend.backend.service;

import com.backend.backend.domain.Veiculo;
import com.backend.backend.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Text;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDate;
import java.util.*;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo adicionarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    public List<Veiculo> encontrarVeiculosPorMarcaAnoCor(String marca, Integer ano, String cor) {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        List<Veiculo> selectedVeiculos = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getMarca().equals(marca) && veiculo.getAno().equals(ano) && veiculo.getCor().equals(cor)) {
                selectedVeiculos.add(veiculo);
            }
        }
        return selectedVeiculos;
    }

    public Veiculo encontrarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id).get();

    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculoAtualizado) {
        Veiculo veiculo = veiculoRepository.findById(id).get();
        veiculo.setVeiculo(veiculoAtualizado.getVeiculo());
        veiculo.setMarca(veiculoAtualizado.getMarca());
        veiculo.setAno(veiculoAtualizado.getAno());
        veiculo.setDescricao(veiculo.getDescricao());
        veiculo.setVendido(veiculoAtualizado.getVendido());
        veiculo.setCreated(veiculoAtualizado.getCreated());
        veiculo.setUpdated(veiculoAtualizado.getCreated());
        veiculo.setCor(veiculoAtualizado.getCor());
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculoParcialmente(Long id, Map<String, Object> campos) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
        if (!veiculoOptional.isPresent()) {
            throw new NoSuchElementException("Veículo não encontrado com o ID: " + id);
        }
        Veiculo veiculo = veiculoOptional.get();

 //       veiculoRepository.update(veiculo);
//        campos.forEach((campo, valor) -> {
//            switch (campo) {
//                case "veiculo":
//                    veiculo.setVeiculo((String) valor);
//                    break;
//                case "marca":
//                    veiculo.setMarca((String) valor);
//                    break;
//                case "ano":
//                    veiculo.setAno((Integer) valor);
//                    break;
//                case "descricao":
//                    veiculo.setDescricao((String) valor);
//                    break;
//                case "vendido":
//                    veiculo.setVendido((Boolean) valor);
//                    break;
//                case "cor":
//                    veiculo.setCor((String) valor);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Campo inválido: " + campo);
//            }
//        });
        return veiculoRepository.save(veiculo);
    }

    public void apagarVeiculo(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id).get();
        veiculoRepository.delete(veiculo);
    }

    public List<Veiculo> encontrarVeiculosNaoVendidos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        List<Veiculo> selectedVeiculos = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getVendido().equals(false)) {
                selectedVeiculos.add(veiculo);
            }
        }
        return selectedVeiculos;
    }

    public List<List<Veiculo>> findVeiculosPorDecada() {

        // Obtém a lista de quantidades de veículos por década
        List<Object[]> veiculosPorDecada = veiculoRepository.countVeiculosPorDecada();

        // Cria uma lista para armazenar os veículos de cada década
        List<List<Veiculo>> veiculosPorDecadaList = new ArrayList<>();

        // Para cada década, busca os veículos correspondentes
        for (Object[] result : veiculosPorDecada) {
            Integer decada = (Integer) result[0];

            // Busca os veículos correspondentes à década
          //  List<Veiculo> byAnoBetween = ;

            // Adiciona a lista de veículos da década na lista de resultado
            veiculosPorDecadaList.add(veiculoRepository.findByAnoBetween(decada, decada + 9));
        }

        return veiculosPorDecadaList;
    }

    public Map<String, List<Veiculo>> encontrarVeiculosPorFabricantes() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        List<String> fabricantes = new ArrayList<>();
        Map<String, List<Veiculo>> campos = new HashMap<>();

        for (Veiculo veiculo : veiculos) {
            if (!fabricantes.contains(veiculo.getMarca())) {
                fabricantes.add(veiculo.getMarca());
            }
        }

        for (String key : fabricantes) {
            List<Veiculo> veiculosPorFabricante = new ArrayList<>();
            for (Veiculo veiculo : veiculos) {
                if (veiculo.getMarca().equals(key)) {
                    veiculosPorFabricante.add(veiculo);
                }
            }
            campos.put(key, veiculosPorFabricante);
        }

        return campos;
    }

    public List<String> encontrarMarcas() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        Set<String> marcas = new HashSet<>();
        for (Veiculo veiculo : veiculos) {
            String marca = veiculo.getMarca().toUpperCase();
            if (isMarcaValida(marca)) {
                marcas.add(marca);
            }
        }
        return new ArrayList<>(marcas);
    }

    public List<Veiculo> buscarVeiculosultimaSemana() {

        LocalDate hoje = LocalDate.now();

        // Calcula a data de uma semana atrás
        LocalDate umaSemanaAtras = hoje.minusWeeks(2);

        // Obtém os veículos registrados na última semana
        List<Veiculo> veiculosUltimaSemana = veiculoRepository.findByCreatedBetween(umaSemanaAtras, hoje);

        return veiculosUltimaSemana;
    }

    private boolean isMarcaValida(String marca) {
        return marca.equals("CHEVROLET") || marca.equals("FIAT") || marca.equals("FORD") || marca.equals("VOLKSWAGEN")
                || marca.equals("TOYOTA") || marca.equals("HONDA") || marca.equals("HYUNDAI") || marca.equals("RENAULT");
    }

}
