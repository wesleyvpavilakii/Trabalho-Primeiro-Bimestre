package br.unipar.devbeckend.trabalhopribimestre.controller;

import br.unipar.devbeckend.trabalhopribimestre.model.Usuario;
import br.unipar.devbeckend.trabalhopribimestre.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios") //caminho postman
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) { // injeção do repository (spring cuida disso)
        this.usuarioRepository = usuarioRepository;
    }

    // cria usuário (checando username único)
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        usuarioRepository.findByUsername(usuario.getUsername())
                .ifPresent(u -> {
                    throw new RuntimeException("Username já existe");
                });

        return usuarioRepository.save(usuario);
    }

    // edita usuário
    @PutMapping("/{id}")
    public Usuario editar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não existe"));

        existente.setNome(usuario.getNome());
        existente.setUsername(usuario.getUsername());

        return usuarioRepository.save(existente);
    }

    // lista os usuários
    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
}
