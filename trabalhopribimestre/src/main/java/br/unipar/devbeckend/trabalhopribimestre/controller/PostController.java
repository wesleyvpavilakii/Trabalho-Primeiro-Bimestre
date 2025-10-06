package br.unipar.devbeckend.trabalhopribimestre.controller;

import br.unipar.devbeckend.trabalhopribimestre.model.Post;
import br.unipar.devbeckend.trabalhopribimestre.model.Usuario;
import br.unipar.devbeckend.trabalhopribimestre.repository.PostRepository;
import br.unipar.devbeckend.trabalhopribimestre.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts") //caminho
public class PostController {

    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;

    public PostController(PostRepository postRepository, UsuarioRepository usuarioRepository) {
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // cria post associado a um usuário
    @PostMapping
    public Post criar(@RequestParam Long usuarioId, @RequestBody Post post) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não existe"));

        post.setAutor(usuario); // associa o usuário ao post
        return postRepository.save(post);
    }

    // lista os posts em ordem cronológica
    @GetMapping("/cronologico")
    public List<Post> porCronologico() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // lista os posts em ordem de relevancia
    @GetMapping("/relevancia")
    public List<Post> porRelevancia() {
        return postRepository.findAllByOrderByLikesCountDesc();
    }
}
