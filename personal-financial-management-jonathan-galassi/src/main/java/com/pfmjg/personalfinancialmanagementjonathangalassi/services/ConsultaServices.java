package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Consulta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServices {

    @Autowired
    private ConsultaRepository consultaRepository;

    public ConsultaServices() {

    }

    public boolean verificarId(Integer id) {
        return consultaRepository.existsById(id);
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Consulta findbyId(Integer id) {
        Optional<Consulta> obj = consultaRepository.findById(id);
        return obj.get();
    }

    public Consulta insertConsulta(Consulta consulta) {

        System.out.println("Valor recebido para o campo idConsulta: " + consulta.getIdConsulta());

        if (verificarId(consulta.getIdConsulta())) {
            throw new IllegalArgumentException("O ID já existe");
        }
//        Paciente existPaciente = categoriaRepository.findByNomePacienteAndAndSobrenomePacienteAndDataNascimentoPaciente(paciente.getNomePaciente(),
//                categoria.getSobrenomePaciente(), categoria.getDataNascimentoPaciente());
//        if (existPaciente != null) {
//            throw new DataIntegrityViolationException("Já existe esse usuário");
//        }
        return consultaRepository.save(consulta);
    }

    public void deleteById(Integer id) {
        consultaRepository.deleteById(id);
    }

    public Consulta updateConsulta(Integer id, Consulta consulta) {

        System.out.println("Valor recebido para o campo idConsulta: " + consulta.getIdConsulta());

        Consulta entity = consultaRepository.getReferenceById(id);
        updateData(entity, consulta);
        return consultaRepository.save(entity);
    }

    private void updateData(Consulta entity, Consulta consulta) {
        entity.setDataConsultaAlterada(consulta.getDataConsultaAlterada());
        entity.setDataConsultaAntiga(consulta.getDataConsultaAntiga());
        entity.setDataConsultaAtual(consulta.getDataConsultaAtual());
        entity.setPaciente(consulta.getPaciente());
        entity.setAgenda(consulta.getAgenda());
        entity.setTipoConsulta(consulta.getTipoConsulta());
        entity.setFormaPagamento(consulta.getFormaPagamento());
        entity.setMesesAcompanhado(consulta.getMesesAcompanhado());
    }
}
