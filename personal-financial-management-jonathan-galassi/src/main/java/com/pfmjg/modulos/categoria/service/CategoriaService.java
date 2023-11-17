package com.pfmjg.modulos.categoria.service;

import com.pfmjg.modulos.categoria.dto.CategoriaRequest;
import com.pfmjg.modulos.categoria.dto.CategoriaResponse;
import com.pfmjg.modulos.categoria.model.Categoria;
import com.pfmjg.modulos.categoria.predicate.CategoriaPredicate;
import com.pfmjg.modulos.categoria.repository.CategoriaRepository;
import com.pfmjg.modulos.comum.exception.NotFoundException;
import com.pfmjg.modulos.comum.exception.ValidacaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
    }

    public List<Categoria> buscarPorIds(List<Integer> ids) {
        var categorias = findByCategoriaId(ids);

        if (categorias.isEmpty()) {
            throw new NotFoundException("Categoria não encontrada");
        }

        return categorias;
    }

    public List<CategoriaResponse> buscarTodos() {
        return repository.findAll().stream().map(CategoriaResponse::of).toList();
    }

    public CategoriaResponse salvar(CategoriaRequest request) {
        validarDescricaoExistente(request.descricao());
        var categoria = Categoria.of(request);
        return CategoriaResponse.of(repository.save(categoria));
    }

    public CategoriaResponse alterar(Integer id, CategoriaRequest request) {
        validarDescricaoExistente(request.descricao(), id);

        var categoriaAntiga = buscarPorId(id);
        var categoriaNova = Categoria.of(request);

        BeanUtils.copyProperties(categoriaNova, categoriaAntiga, "id", "situacao");

        return CategoriaResponse.of(repository.save(categoriaAntiga));
    }

    public void deletar(Integer id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new ValidacaoException("Não é possível excluir categoria, existem nutricionistas vinculados a ela.");
        } catch (Exception ex) {
            throw new ValidacaoException("Ocorreu um erro ao excluir o item. " + ex.getMessage());
        }
    }

    private List<Categoria> findByCategoriaId(List<Integer> ids) {
        var categoriaPredicate = new CategoriaPredicate();
        var predicate = categoriaPredicate.comIds(ids).build();
        return (List<Categoria>) repository.findAll(predicate);
    }

    private void validarDescricaoExistente(String descricao) {
        if (repository.existsByDescricao(descricao)) {
            throw new ValidacaoException("Descricao já cadastrada");
        }
    }

    private void validarDescricaoExistente(String descricao, Integer id) {
        if (repository.existsByDescricaoAndIdNot(descricao, id)) {
            throw new ValidacaoException("Descricao já cadastrada");
        }
    }
}
