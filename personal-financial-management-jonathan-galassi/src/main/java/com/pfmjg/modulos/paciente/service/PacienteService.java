package com.pfmjg.modulos.paciente.service;

import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.comum.exception.NotFoundException;
import com.pfmjg.modulos.comum.exception.ValidacaoException;
import com.pfmjg.modulos.paciente.dto.PacienteFiltros;
import com.pfmjg.modulos.paciente.dto.PacienteRequest;
import com.pfmjg.modulos.paciente.dto.PacienteResponse;
import com.pfmjg.modulos.paciente.model.Paciente;
import com.pfmjg.modulos.paciente.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.pfmjg.modulos.comum.util.DateUtil.validarDataNoFuturo;
import static com.pfmjg.modulos.comum.util.StringUtil.removerCaracteresNaoNumericos;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));
    }

    public List<PacienteResponse> buscarTodos(PacienteFiltros filtros) {
        var pacientes = (List<Paciente>) repository.findAll(filtros.toPredicate().build());
        return pacientes.stream()
                .map(PacienteResponse::of)
                .collect(Collectors.toList());
    }

    public PacienteResponse salvar(PacienteRequest request) {
        validarDataNoFuturo(request.dataNascimento());
        validarCpfExistente(request.cpf());
        var paciente = Paciente.of(request);
        return PacienteResponse.of(repository.save(paciente));
    }

    public PacienteResponse alterar(Integer id, PacienteRequest request) {
        validarDataNoFuturo(request.dataNascimento());
        validarCpfExistente(request.cpf(), id);

        var pacienteAntigo = buscarPorId(id);
        var pacienteNovo = Paciente.of(request);

        BeanUtils.copyProperties(pacienteNovo, pacienteAntigo, "id", "situacao");

        return PacienteResponse.of(repository.save(pacienteAntigo));
    }

    public void ativar(Integer id) {
        var paciente = buscarPorId(id);
        paciente.setSituacao(ESituacao.ATIVO);

        repository.save(paciente);
    }

    public void inativar(Integer id) {
        var paciente = buscarPorId(id);
        paciente.setSituacao(ESituacao.INATIVO);

        repository.save(paciente);
    }

    private void validarCpfExistente(String cpf) {
        if (repository.existsByCpf(removerCaracteresNaoNumericos(cpf))) {
            throw new ValidacaoException("Paciente já cadastrado com esse cpf");
        }
    }

    private void validarCpfExistente(String cpf, Integer id) {
        if (repository.existsByCpfAndIdNot(removerCaracteresNaoNumericos(cpf), id)) {
            throw new ValidacaoException("Paciente já cadastrado com esse cpf");
        }
    }
}
