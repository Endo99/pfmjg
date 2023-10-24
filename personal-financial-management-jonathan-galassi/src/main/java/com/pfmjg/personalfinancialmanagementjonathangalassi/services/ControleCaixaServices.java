package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta.Consulta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.conta.Conta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa.ControleCaixa;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ContaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ControleCaixaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControleCaixaServices {

    @Autowired
    private ControleCaixaRepository controleCaixaRepository;

    @Autowired
    private ContaRepository contaRepository;

    public void enrollControleCaixaInConta(Integer idControleCaixa, Integer idConta) {
        ControleCaixa controleCaixa = controleCaixaRepository.findById(idControleCaixa).orElse(null);
        Conta conta = contaRepository.findById(idConta).orElse(null);

        if (controleCaixa != null && conta != null) {
            controleCaixa.getContas().add(conta);
            conta.getControleCaixa().add(controleCaixa);
            controleCaixaRepository.save(controleCaixa);
            contaRepository.save(conta);
        }
    }

    public List<ControleCaixa> findAll() {
        return controleCaixaRepository.findAll();
    }



    public ControleCaixa insertControleCaixa(Integer idConta, ControleCaixa controleCaixa ) {
        try {
            // Busque o paciente correspondente ao ID
            Conta conta = contaRepository.findById(idConta)
                    .orElseThrow(() -> new EntityNotFoundException("Conta não encontrado com o ID: " + idConta));

            // Associe o paciente à consulta
            conta.getControleCaixa().add(controleCaixa);

            // Salve o ControleCaixa no banco de dados
            contaRepository.save(conta);

            // A seguir, você deve definir a relação inversa, associando o ControleCaixa à conta.
            // Isso depende da estrutura do seu modelo de dados. Se a relação for bidirecional,
            // você também deve configurar a conta no ControleCaixa.
            controleCaixa.getContas().add(conta);

            // Salve a consulta no banco de dados
            return controleCaixaRepository.save(controleCaixa);
        } catch (EntityNotFoundException e) {
            throw e; // Você pode lançar a exceção novamente ou tratar de acordo com suas necessidades.
        } catch (Exception exception) {
            throw exception; // Trate outras exceções de acordo com suas necessidades.
        }
    }

    public ControleCaixa cadastrarControleCaixa(ControleCaixa controleCaixa) {
        // 1. Validação de dados (se necessário)
        // Aqui você pode adicionar validações específicas, se necessário.

        // 2. Persistência no repositório
        return controleCaixaRepository.save(controleCaixa);
    }

    public void deleteById(Integer id) {
        controleCaixaRepository.deleteById(id);
    }

    public ControleCaixa updateControleCaixaa(Integer id, ControleCaixa controleCaixa) {

        System.out.println("Valor recebido para o campo idControleCaixa: " + controleCaixa.getIdControleCaixa());

        ControleCaixa entity = controleCaixaRepository.getReferenceById(id);
        updateData(entity, controleCaixa);
        return controleCaixaRepository.save(entity);
    }

    private void updateData(ControleCaixa entity, ControleCaixa controleCaixa) {
        entity.setData(controleCaixa.getData());
        entity.setProdutoOuCliente(controleCaixa.getProdutoOuCliente());
        entity.setPreco(controleCaixa.getPreco());
        entity.setFormaPagamento(controleCaixa.getFormaPagamento());
    }

}
