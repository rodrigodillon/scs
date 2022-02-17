package br.com.scs.api.controller;

import br.com.scs.api.dto.SenhaDTO;
import br.com.scs.model.entity.Senha;
import br.com.scs.model.enums.TipoSenha;
import br.com.scs.service.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/senhas")
public class SenhaController {

    @Autowired
    private SenhaService senhaService;

    @GetMapping
    public ResponseEntity buscarSenha() {
        List<Senha> senhas = senhaService.buscarTodos();
        return ResponseEntity.ok(senhas);
    }

    @GetMapping("/senha-atual")
    public ResponseEntity buscarSenhaAtual() {
        Senha senha = senhaService.obterSenhaAtual();
        return ResponseEntity.ok(senha);
    }

    @PostMapping("/gerar-nova-senha")
    public ResponseEntity gerarNovaSenha(@RequestBody SenhaDTO dto) {
        try {
            Senha novaSenha = senhaService.gerarSenha(TipoSenha.valueOf(dto.getTipo()));
            return ResponseEntity.ok(novaSenha);

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reiniciar")
    public ResponseEntity.BodyBuilder reiniciarSenhas() {
            senhaService.reiniciarSenhas();
            return ResponseEntity.ok();
    }

    @PostMapping("/chamar-proximo")
    public ResponseEntity chamarProximo() {
        try {
            Senha proximSenha = senhaService.pedirProximaSenha();
            return ResponseEntity.ok(proximSenha);

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
