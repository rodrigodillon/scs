package br.com.scs.service;

import br.com.scs.model.entity.Senha;
import br.com.scs.model.enums.TipoSenha;

import java.util.List;

public interface SenhaService {

    Senha gerarSenha(TipoSenha tipoSenha);

    Senha pedirProximaSenha();

    void reiniciarSenhas();

    Senha obterSenhaAtual();

    List<Senha> buscarTodos();

}
