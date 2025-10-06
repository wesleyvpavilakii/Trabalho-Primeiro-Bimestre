package br.unipar.devbeckend.trabalhopribimestre.controller;

import br.unipar.devbeckend.trabalhopribimestre.model.Usuario;
import br.unipar.devbeckend.trabalhopribimestre.repository.UsuarioRepository;
import br.unipar.devbeckend.trabalhopribimestre.services.CurtidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/curtir") //caminho do do postman

public class CurtidaController {
    private final CurtidaService curtidaService;
    public CurtidaController(CurtidaService curtidaService) {
        this.curtidaService = curtidaService;
    }

    //caminho pra criar um post
    @PostMapping("/{usuarioId}")
    public ResponseEntity<String> curtir(@PathVariable Long postId, @PathVariable Long usuarioId) {
        curtidaService.like(postId, usuarioId);
        return ResponseEntity.ok("Curtida com sucesso!");
    }

    //caminho pra deletar um post
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<String> deletar(@PathVariable Long postId, @PathVariable Long usuarioId) {
        curtidaService.unlike(postId, usuarioId);
        return ResponseEntity.ok("Curtida removida com sucesso!");
    }

}
