package br.com.scs.model.entity;

import javax.persistence.*;

@Entity
@Table( name = "senha_controle")
public class ControleSenha {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Long id;

    @Column(name= "senha_preferencial")
    private Integer senhaPreferencial;

    @Column(name= "senha_normal")
    private Integer senhaNormal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSenhaPreferencial() {
        return senhaPreferencial;
    }

    public void setSenhaPreferencial(Integer senhaPreferencial) {
        this.senhaPreferencial = senhaPreferencial;
    }

    public Integer getSenhaNormal() {
        return senhaNormal;
    }

    public void setSenhaNormal(Integer senhaNormal) {
        this.senhaNormal = senhaNormal;
    }
}
