package com.guardiaodigital.service;

import com.guardiaodigital.model.Comunicacao;
import com.guardiaodigital.repository.ComunicacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AnaliseComunicacaoService {

    @Autowired
    private final ComunicacaoRepository repo;

    public AnaliseComunicacaoService(ComunicacaoRepository repo) {
        this.repo = repo;
    }

    private final List<String> telefonesVerificados = List.of("11999998888");
    private final List<String> emailsVerificados = List.of("familia@exemplo.com");
    private final List<String> palavrasChave = Arrays.asList("urgente", "pix", "neto preso");

    public boolean isSuspeita(Comunicacao comunicacao) {
        boolean confiavel = telefonesVerificados.contains(comunicacao.getTelefone())
                || emailsVerificados.contains(comunicacao.getEmail());
        boolean palavrasSuspeitas = palavrasChave.stream().anyMatch(p -> comunicacao.getMensagem().toLowerCase().contains(p));
        repo.save(comunicacao);
        return !confiavel && palavrasSuspeitas;
    }

    public List<String> getTelefonesVerificados() {
        return telefonesVerificados;
    }

    public List<String> getEmailsVerificados() {
        return emailsVerificados;
    }

    public List<String> getPalavrasChave() {
        return palavrasChave;
    }
}
