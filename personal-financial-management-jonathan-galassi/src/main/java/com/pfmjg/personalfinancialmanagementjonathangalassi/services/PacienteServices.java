package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.PacienteDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServices {

    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteServices() {

    }

    public boolean verificarId(Integer id) {
        return pacienteRepository.existsById(id);
    }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Paciente findbyId(Integer id) {
        Optional<Paciente> obj = pacienteRepository.findById(id);
        return obj.get();
    }

    public List<String> searchByName(String nomePaciente) {

        return  pacienteRepository.searchByName(nomePaciente);
    }

    public Paciente insertPaciente(Paciente paciente) {

        System.out.println("Valor recebido para o campo idPaciente: " + paciente.getIdPaciente());
        System.out.println("Valor recebido para o campo nomePaciente: " + paciente.getNomePaciente());
        System.out.println("Valor recebido para o campo idadePaciente: " + paciente.getIdadePaciente());
        System.out.println("Valor recebido para o campo dataPaciente: " + paciente.getDataNascimentoPaciente());
        System.out.println("Valor recebido para o campo cidade: " + paciente.getCidade());
        System.out.println("Valor recebido para o campo estado: " + paciente.getEstado());
        System.out.println("Valor recebido para o campo telefone: " + paciente.getTelefone());

//        Paciente paciente1 = pacienteRepository.save(paciente);
//        int gerar = paciente1.getIdPaciente();

//        System.out.println("Paciente gerado com o id número: " + gerar);

//        if (verificarId(paciente.getIdPaciente())) {
//            throw new IllegalArgumentException("O ID já existe");
//        }
        Paciente existPaciente = pacienteRepository.findByNomePacienteAndDataNascimentoPaciente(paciente.getNomePaciente(),  paciente.getDataNascimentoPaciente());
        if (existPaciente != null) {
            throw new DataIntegrityViolationException("Já existe esse usuário");
        }
        return pacienteRepository.save(paciente);
    }

    public void deleteById(Integer id) {
        pacienteRepository.deleteById(id);
    }

    public Paciente updatePaciente(Integer idPaciente, Paciente paciente) {

        Paciente entity = pacienteRepository.getReferenceById(idPaciente);
        updateData(entity, paciente);
        return pacienteRepository.save(entity);
    }

    private void updateData(Paciente entity, Paciente paciente) {

        entity.setNomePaciente(paciente.getNomePaciente());
        entity.setDataNascimentoPaciente(paciente.getDataNascimentoPaciente());
        entity.setCidade(paciente.getCidade());
        entity.setTelefone(paciente.getTelefone());
        entity.setEstado(paciente.getEstado());
        entity.setIdadePaciente(paciente.getIdadePaciente());
    }

    public List<Integer> getAllPacienteIds() {
        return pacienteRepository.findAllId();
    }

    public List<Paciente> getCpfEIdPaciente(String cpf, Integer idPaciente) {
        return pacienteRepository.findPacienteByCpfAndIdPaciente(cpf, idPaciente);
    }

    public List<String> listarTodosOsCpfs() {
        return pacienteRepository.findAllCpfs();
    }

    public PacienteDTO getDetalhesDoPacientePorCPF(String cpf) {
        // Implemente a lógica para buscar detalhes do paciente com base no CPF
        Paciente paciente = pacienteRepository.findByCpf(cpf); // Substitua findByCpf pelo método real

        if (paciente != null) {
            // Mapeie os detalhes do paciente para um DTO
            PacienteDTO pacienteDTO = new PacienteDTO();
            pacienteDTO.setIdPaciente(paciente.getIdPaciente());
            pacienteDTO.setNomePaciente(paciente.getNomePaciente());
            pacienteDTO.setCpf(paciente.getCpf());
            pacienteDTO.setDataNascimentoPaciente(paciente.getDataNascimentoPaciente());
            pacienteDTO.setIdadePaciente(paciente.getIdadePaciente());
            pacienteDTO.setCidade(paciente.getCidade());
            pacienteDTO.setEstado(paciente.getEstado());
            pacienteDTO.setTelefone(paciente.getTelefone());
            // Mapeie outros campos conforme necessário

            return pacienteDTO;
        } else {
            return null; // Ou lance uma exceção se o paciente não for encontrado
        }
    }

//    @PostMapping("/cadastrar")
//    @Transactional
//    @ResponseBody
//    public List<Pacientes> cadastrarPaciente(@RequestBody Pacientes pacientes) {
//        return (List<Pacientes>) new pacientesRepository.save(pacientes);
//    }
//
//    @PostMapping("/editar/{id}")
//    @Transactional
//    @ResponseBody
//    public String editarDadoPaciente( @RequestBody Pacientes pacientes) {
//        return "";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    @Transactional
//    @ResponseBody
//    public String deletarPaciente(Pacientes pacientes) {
//
//        return "";
//    }

}

