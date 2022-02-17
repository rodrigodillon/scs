package br.com.scs.service.impl;

import br.com.scs.model.entity.ControleSenha;
import br.com.scs.model.repository.ControleSenhaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class SenhaServiceImplTest {

    @Autowired
    private SenhaServiceImpl senhaService;

    @Autowired
    private ControleSenhaRepository controleSenhaRepository;

    @Test
    public void reiniciarSenhas_TabelaSenhaControleSemRegistros_ContadoresReinicializadosParaValorUm() throws SQLException {
        // apagar todos os registros da tabela
        controleSenhaRepository.deleteAll();

        // Reiniciar as senhas
        senhaService.reiniciarSenhas();

        // validar se os registros estao com valor 1
        ControleSenha controleSenha = controleSenhaRepository.findAll().iterator().next();
        Assertions.assertEquals(1, controleSenha.getSenhaNormal());
        Assertions.assertEquals(1, controleSenha.getSenhaPreferencial());
    }

    @Test
    public void reiniciarSenhas_TabelaSenhaControleComRegistro_ContadoresReinicializadosParaValorUm() throws SQLException {
        // apagar todos os registros da tabela
        controleSenhaRepository.deleteAll();

        // Inserir um novo registro
        ControleSenha controleSenha = new ControleSenha();
        controleSenha.setSenhaPreferencial(5);
        controleSenha.setSenhaNormal(15);
        controleSenhaRepository.save(controleSenha);

        // Reiniciar as senhas
        senhaService.reiniciarSenhas();

        // validar se os registros estao com valor 1
        controleSenha = controleSenhaRepository.findAll().iterator().next();
        Assertions.assertEquals(1, controleSenha.getSenhaNormal());
        Assertions.assertEquals(1, controleSenha.getSenhaPreferencial());
    }

}