package br.com.scs.model.entity;

import br.com.scs.model.enums.StatusAtendimento;
import br.com.scs.model.enums.TipoSenha;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "senha")
public class Senha {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Long id;

    @Column(name= "senha")
    private String senha;

    @Column(name = "data_hora_senha_gerada")
    private LocalDateTime dataHoraSenhaGerada;

    @Column(name = "data_hora_senha_atendimento")
    private LocalDateTime dataHoraSenhaAtendimento;

    @Column(name= "tipo")
    @Enumerated(value = EnumType.STRING)
    private TipoSenha tipo;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusAtendimento status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataHoraSenhaGerada() {
        return dataHoraSenhaGerada;
    }

    public void setDataHoraSenhaGerada(LocalDateTime dataHoraSenhaGerada) {
        this.dataHoraSenhaGerada = dataHoraSenhaGerada;
    }

    public LocalDateTime getDataHoraSenhaAtendimento() {
        return dataHoraSenhaAtendimento;
    }

    public void setDataHoraSenhaAtendimento(LocalDateTime dataHoraSenhaAtendimento) {
        this.dataHoraSenhaAtendimento = dataHoraSenhaAtendimento;
    }

    public TipoSenha getTipo() {
        return tipo;
    }

    public void setTipo(TipoSenha tipo) {
        this.tipo = tipo;
    }

    public StatusAtendimento getStatus() {
        return status;
    }

    public void setStatus(StatusAtendimento status) {
        this.status = status;
    }
}
