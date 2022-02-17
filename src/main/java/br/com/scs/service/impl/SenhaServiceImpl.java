package br.com.scs.service.impl;

import br.com.scs.model.entity.ControleSenha;
import br.com.scs.model.entity.Senha;
import br.com.scs.model.enums.StatusAtendimento;
import br.com.scs.model.enums.TipoSenha;
import br.com.scs.model.repository.ControleSenhaRepository;
import br.com.scs.model.repository.SenhaRepository;
import br.com.scs.service.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SenhaServiceImpl implements SenhaService {

    @Autowired
    private SenhaRepository senhaRepository;

    @Autowired
    private ControleSenhaRepository controleSenhaRepository;

    @Override
    @Transactional
    public Senha gerarSenha(TipoSenha tipoSenha) {
        ControleSenha controleSenha = controleSenhaRepository.findAll().get(0);
        int senhaProxima = -1;
        Senha senha = new Senha();
        senha.setStatus(StatusAtendimento.PENDENTE);
        senha.setDataHoraSenhaGerada(LocalDateTime.now());

        if(tipoSenha == TipoSenha.NORMAL) {
            senhaProxima = controleSenha.getSenhaNormal() + 1;
            senha.setTipo(TipoSenha.NORMAL);
            senha.setSenha("N" + String.format("%04d", senhaProxima));
            controleSenha.setSenhaNormal(senhaProxima);
        }
        if(tipoSenha == TipoSenha.PREFERENCIAL) {
            senhaProxima = controleSenha.getSenhaPreferencial() + 1;
            senha.setTipo(TipoSenha.PREFERENCIAL);
            senha.setSenha("P" + String.format("%04d", senhaProxima));
            controleSenha.setSenhaPreferencial(senhaProxima);
        }

        senhaRepository.save(senha);
        controleSenhaRepository.save(controleSenha);

        return senha;
    }

    @Override
    @Transactional
    public Senha pedirProximaSenha() {

        Senha senhaPreferencial = senhaRepository.obterSenhaPreferencial();
        if(senhaPreferencial != null) {
            senhaPreferencial.setStatus(StatusAtendimento.CONCLUIDO);
            senhaPreferencial.setDataHoraSenhaAtendimento(LocalDateTime.now());
            senhaRepository.save(senhaPreferencial);
            return senhaPreferencial;
        }else {

            Senha senhaNormal = senhaRepository.obterSenhaNormal();

            if(senhaNormal != null) {
                senhaNormal.setStatus(StatusAtendimento.CONCLUIDO);
                senhaNormal.setDataHoraSenhaAtendimento(LocalDateTime.now());
                senhaRepository.save(senhaNormal);
                return senhaNormal;
            }

        }
        return null;
    }

    @Override
    @Transactional
    public void reiniciarSenhas() {
        ControleSenha controleSenha;
        List<ControleSenha> controles = controleSenhaRepository.findAll();
        if (controles.isEmpty()) {
            controleSenha = new ControleSenha();
        } else {
            controleSenha = controles.get(0);
        }
        controleSenha.setSenhaNormal(1);
        controleSenha.setSenhaPreferencial(1);
        controleSenhaRepository.save(controleSenha);
    }

    @Override
    @Transactional
    public Senha obterSenhaAtual() {
        return senhaRepository.obterSenhaAtual();

    }

    @Override
    public List<Senha> buscarTodos() {
        return senhaRepository.findAll();
    }


}
