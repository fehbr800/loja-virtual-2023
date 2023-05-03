package com.dev.backend.service;

import org.springframework.stereotype.Service;

import com.dev.backend.dto.PessoaClienteRequestDTO;
import com.dev.backend.entity.Pessoa;

import com.dev.backend.repository.PessoaClienteRepository;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PessoaClienteService {
    @Autowired
    private PessoaClienteRepository pessoaRepository;

    @Autowired
    private PermissaoPessoaService permissaoPessoaService;

    @Autowired
    private EmailService emailService;

    public Pessoa registrar(PessoaClienteRequestDTO pessoaClienteRequestDTO) {
        Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);

        pessoa.setDataCriacao(new Date());
        Pessoa pessoaNova = pessoaRepository.saveAndFlush(pessoa);
        permissaoPessoaService.vincularPessoaPermissaoCliente(pessoaNova);
        // emailService.enviarEmailTexto(pessoaNova.getEmail(), "Cadastro na Loja: Atletismo Shoes  ", "Cadastro realizado com Sucesso. Em breve você receberá a senha de acesso por e-mail!!");
        Map<String, Object> propMap = new HashMap<>();
        propMap.put("nome", pessoaNova.getNome());
        propMap.put("mensagem", "Cadastro realizado com Sucesso. Em breve você receberá a senha de acesso por e-mail!!");
        emailService.enviarEmailTemplate(pessoaNova.getEmail(), "Cadastro na Loja: Atletismo Shoes", propMap);
        return pessoaNova;
    }

}
