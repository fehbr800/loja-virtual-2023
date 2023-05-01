package com.dev.backend.service;

import org.springframework.stereotype.Service;

import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;



@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public Pessoa inserir(Pessoa pessoa) {
        pessoa.setDataAtualizacao(new Date());
        Pessoa pessoaNova = pessoaRepository.saveAndFlush(pessoa);
        return pessoaNova;
    }

    public Pessoa alterar(Pessoa pessoa) {
        pessoa.setDataAtualizacao(new Date());
        return pessoaRepository.saveAndFlush(pessoa);
    }

    public void excluir(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).get();
        pessoaRepository.delete(pessoa);
    }
}
