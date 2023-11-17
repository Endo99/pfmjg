package com.pfmjg.modulos.nutricionista.service;

import com.pfmjg.modulos.categoria.service.CategoriaService;
import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.comum.exception.NotFoundException;
import com.pfmjg.modulos.comum.exception.ValidacaoException;
import com.pfmjg.modulos.nutricionista.dto.NutricionistaFiltros;
import com.pfmjg.modulos.nutricionista.dto.NutricionistaRequest;
import com.pfmjg.modulos.nutricionista.dto.NutricionistaResponse;
import com.pfmjg.modulos.nutricionista.model.Nutricionista;
import com.pfmjg.modulos.nutricionista.repository.NutricionistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.pfmjg.modulos.comum.util.StringUtil.removerCaracteresNaoNumericos;

@Service
@RequiredArgsConstructor
public class NutricionistaService {

    private final NutricionistaRepository repository;
    private final CategoriaService categoriaService;

    public Nutricionista buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nutricionista não encontrado"));
    }

    public List<NutricionistaResponse> buscarTodos(NutricionistaFiltros filtros) {
        var nutricionista = (List<Nutricionista>) repository.findAll(filtros.toPredicate().build());
        return nutricionista.stream()
                .map(NutricionistaResponse::of)
                .collect(Collectors.toList());
    }

    public NutricionistaResponse salvar(NutricionistaRequest request) {
        var categorias = categoriaService.buscarPorIds(request.categoriasIds());
        validarCpfExistente(request.cpf());
        var nutricionista = Nutricionista.of(request, categorias);
        return NutricionistaResponse.of(repository.save(nutricionista));
    }

    public NutricionistaResponse alterar(Integer id, NutricionistaRequest request) {
        validarCpfExistente(request.cpf(), id);
        var categorias = categoriaService.buscarPorIds(request.categoriasIds());

        var nutricionistaAntigo = buscarPorId(id);
        var nutricionistaNovo = Nutricionista.of(request, categorias);

        BeanUtils.copyProperties(nutricionistaNovo, nutricionistaAntigo, "id", "situacao");

        return NutricionistaResponse.of(repository.save(nutricionistaAntigo));
    }

    public void ativar(Integer id) {
        var nutricionista = buscarPorId(id);
        nutricionista.setSituacao(ESituacao.ATIVO);

        repository.save(nutricionista);
    }

    public void inativar(Integer id) {
        var nutricionista = buscarPorId(id);
        nutricionista.setSituacao(ESituacao.INATIVO);

        repository.save(nutricionista);
    }

    private void validarCpfExistente(String cpf) {
        if (repository.existsByCpf(removerCaracteresNaoNumericos(cpf))) {
            throw new ValidacaoException("Nutricionista já cadastrado com esse cpf");
        }
    }

    private void validarCpfExistente(String cpf, Integer id) {
        if (repository.existsByCpfAndIdNot(removerCaracteresNaoNumericos(cpf), id)) {
            throw new ValidacaoException("Nutricionista já cadastrado com esse cpf");
        }
    }
}
