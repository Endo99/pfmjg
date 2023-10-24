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


    public Conta insertConta(Integer idControleCaixa, Conta conta ) {
        try {
            // Busque o paciente correspondente ao ID
            ControleCaixa controleCaixa = controleCaixaRepository.findById(idControleCaixa)
                    .orElseThrow(() -> new EntityNotFoundException("Controle não encontrado com o ID: " + idControleCaixa));

            // Associe o paciente à consulta
            controleCaixa.getContas().add(conta);

            // Salve o ControleCaixa no banco de dados
            controleCaixaRepository.save(controleCaixa);

            // A seguir, você deve definir a relação inversa, associando o ControleCaixa à conta.
            // Isso depende da estrutura do seu modelo de dados. Se a relação for bidirecional,
            // você também deve configurar a conta no ControleCaixa.
            conta.getControleCaixa().add(controleCaixa);

            // Salve a consulta no banco de dados
            return contaRepository.save(conta);
        } catch (EntityNotFoundException e) {
            throw e; // Você pode lançar a exceção novamente ou tratar de acordo com suas necessidades.
        } catch (Exception exception) {
            throw exception; // Trate outras exceções de acordo com suas necessidades.
        }

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
    }
    public void deleteById(Integer id) {
        contaRepository.deleteById(id);
    }

}
