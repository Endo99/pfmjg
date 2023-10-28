package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa.ControleCaixa;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.historicoalteracoes.HistoricoAlteracoes;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ControleCaixaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.HistoricoAlteracoesRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoAlteracoesServices {
    @Autowired
    private HistoricoAlteracoesRepository historicoAlteracoesRepository;
    private PacienteRepository pacienteRepository;
    private ControleCaixaRepository controleCaixaRepository;

    public List<HistoricoAlteracoes> findAll() {
        return historicoAlteracoesRepository.findAll();
    }

    public HistoricoAlteracoes insertHistoricoAlteracoes(HistoricoAlteracoes historicoAlteracoes, Integer idPaciente, Integer idControleCaixa) {
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o ID: " + idPaciente));

        ControleCaixa controleCaixa = controleCaixaRepository.findById(idControleCaixa)
                .orElseThrow(() -> new EntityNotFoundException("ControleCaixa não encontrado com o ID: " + idControleCaixa));

        historicoAlteracoes.setIdPaciente(paciente);
        historicoAlteracoes.setIdControleCaixa(controleCaixa);

        return historicoAlteracoesRepository.save(historicoAlteracoes);
    }

    public HistoricoAlteracoes updateHistoricoAlteracoes(Integer id, HistoricoAlteracoes historicoAlteracoes) {
        HistoricoAlteracoes existingHistorico = historicoAlteracoesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico de alterações não encontrado com o ID: " + id));

        // Atualize os campos do histórico com os valores do objeto atualizado
        existingHistorico.setDataAlteracao(historicoAlteracoes.getDataAlteracao());
        existingHistorico.setHora(historicoAlteracoes.getHora());
        existingHistorico.setQuantidadePago(historicoAlteracoes.getQuantidadePago());
        existingHistorico.setValorConsulta(historicoAlteracoes.getValorConsulta());
        existingHistorico.setTipoPagamento(historicoAlteracoes.getTipoPagamento());
        existingHistorico.setStatusPagamentoMod(historicoAlteracoes.getStatusPagamentoMod());
        existingHistorico.setValorTransacionado(historicoAlteracoes.getValorTransacionado());

        // Salve o histórico de alterações atualizado
        return historicoAlteracoesRepository.save(existingHistorico);
    }

    public void deleteHistoricoAlteracoes(Integer id) {
        HistoricoAlteracoes historico = historicoAlteracoesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico de alterações não encontrado com o ID: " + id));

        // Exclua o histórico de alterações
        historicoAlteracoesRepository.delete(historico);
    }

}
