package br.com.scs.model.repository;

import br.com.scs.model.entity.Senha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SenhaRepository extends JpaRepository<Senha, Long> {
    @Query(value = "select * from senha where data_hora_senha_atendimento is null and tipo = 'PREFERENCIAL' order by data_hora_senha_gerada limit 1", nativeQuery = true)
    Senha obterSenhaPreferencial();

    @Query(value = "select * from senha where data_hora_senha_atendimento is null and tipo = 'NORMAL' order by data_hora_senha_gerada limit 1", nativeQuery = true)
    Senha obterSenhaNormal();

    @Query(value = "select * from senha where data_hora_senha_atendimento is not null order by data_hora_senha_atendimento desc limit 1  ", nativeQuery = true)
    Senha obterSenhaAtual();
}
