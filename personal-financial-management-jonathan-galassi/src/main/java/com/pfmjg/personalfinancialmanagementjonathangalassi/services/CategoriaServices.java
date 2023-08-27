package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Categoria;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaServices() {

    }

    public boolean verificarId(Integer id) {
        return categoriaRepository.existsById(id);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findbyId(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.get();
    }

    public Categoria insertCategoria(Categoria categoria) {

        System.out.println("Valor recebido para o campo idCategoria: " + categoria.getIdCategoria());
        System.out.println("Valor recebido para o campo tipoCategoria: " + categoria.getTipoCategoria());
        System.out.println("Valor recebido para o campo descricao: " + categoria.getDescricao());

        if (verificarId(categoria.getIdCategoria())) {
            throw new IllegalArgumentException("O ID já existe");
        }
//        Paciente existPaciente = categoriaRepository.findByNomePacienteAndAndSobrenomePacienteAndDataNascimentoPaciente(paciente.getNomePaciente(),
//                categoria.getSobrenomePaciente(), categoria.getDataNascimentoPaciente());
//        if (existPaciente != null) {
//            throw new DataIntegrityViolationException("Já existe esse usuário");
//        }
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Integer id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria updateCategoria(Integer id, Categoria categoria) {

        System.out.println("Valor recebido para o campo idCategoria: " + categoria.getIdCategoria());
        System.out.println("Valor recebido para o campo tipoCategoria: " + categoria.getTipoCategoria());
        System.out.println("Valor recebido para o campo descricao: " + categoria.getDescricao());

        Categoria entity = categoriaRepository.getReferenceById(id);
        updateData(entity, categoria);
        return categoriaRepository.save(entity);
    }

    private void updateData(Categoria entity, Categoria categoria) {
        entity.setTipoCategoria(categoria.getTipoCategoria());
        entity.setDescricao(categoria.getDescricao());
    }

}