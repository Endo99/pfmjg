package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta.Consulta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.conta.Conta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa.ControleCaixa;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ContaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ControleCaixaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaServices {
    private final ContaRepository contaRepository;
    private final ControleCaixaRepository controleCaixaRepository;

    public ContaServices(ContaRepository contaRepository,
                         ControleCaixaRepository controleCaixaRepository) {
        this.contaRepository = contaRepository;
        this.controleCaixaRepository = controleCaixaRepository;
    }

    public List<Conta> findAll() {
        return contaRepository.findAll();
    }



    public Conta cadastrarConta(Conta conta) {
        // 1. Validação de dados (exemplo: verificando se o saldo é válido)
        if (conta.getSaldoAtual() < 0) {
            throw new IllegalArgumentException("O saldo da conta não pode ser negativo.");
        }

        // 2. Persistência no repositório
        return contaRepository.save(conta);
    }

    public Conta updateConta(Integer id, Conta conta) {

        System.out.println("Valor recebido para o campo idConta: " + conta.getIdConta());

        Conta entity = contaRepository.getReferenceById(id);
        updateData(entity, conta);
        return contaRepository.save(entity);
    }

    private void updateData(Conta entity, Conta conta) {
        entity.setControleCaixa(conta.getControleCaixa());
        entity.setSaldoAtual(conta.getSaldoAtual());
        entity.setConta(conta.getConta());
    }
    public void deleteById(Integer id) {
        contaRepository.deleteById(id);
    }

    public Conta atualizarSaldo(Integer idConta, Integer idControleCaixa, Double novoSaldo) {
        // Verifique se o ControleCaixa e a Conta associados existem
        ControleCaixa controleCaixa = controleCaixaRepository.findById(idControleCaixa).orElse(null);
        Conta conta = contaRepository.findById(idConta).orElse(null);

        if (controleCaixa != null && conta != null) {
            // Atualize o saldo da conta
            conta.setSaldoAtual(novoSaldo);

            // Salve a conta atualizada
            conta = contaRepository.save(conta);

            return conta;
        } else {
            throw new EntityNotFoundException("ControleCaixa ou Conta não encontrados.");
        }
    }

}
