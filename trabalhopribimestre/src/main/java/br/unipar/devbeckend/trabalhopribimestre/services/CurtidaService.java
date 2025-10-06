package br.unipar.devbeckend.trabalhopribimestre.services;

import br.unipar.devbeckend.trabalhopribimestre.model.Curtida;
import br.unipar.devbeckend.trabalhopribimestre.model.Post;
import br.unipar.devbeckend.trabalhopribimestre.model.Usuario;
import br.unipar.devbeckend.trabalhopribimestre.repository.CurtidaRepository;
import br.unipar.devbeckend.trabalhopribimestre.repository.PostRepository;
import br.unipar.devbeckend.trabalhopribimestre.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurtidaService {

    private final CurtidaRepository curtidaRepository;
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;

    public CurtidaService(CurtidaRepository curtidaRepository,
                          PostRepository postRepository,
                          UsuarioRepository usuarioRepository) {
        this.curtidaRepository = curtidaRepository;
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional //garante que tudo ou nada é salvo
    public void like(Long postId, Long usuarioId) {
        //busca o post e o usuario
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("O post não foi encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("O usuário não foi encontrado"));

        //se ja curtiu, nao deixa curtir de novo
        if (curtidaRepository.findByPostAndUsuario(post, usuario).isPresent()) {
            throw new RuntimeException("O usuário já curtiu esse post");
        }

        //cria uma nova curtida
        Curtida curtida = new Curtida();
        curtida.setPost(post);
        curtida.setUsuario(usuario);
        curtidaRepository.save(curtida);

        //aumenta o contador de likes
        post.setLikesCount(post.getLikesCount() + 1);
        postRepository.save(post);
    }

    @Transactional
    public void unlike(Long postId, Long usuarioId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não existente"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não existente"));

        //verifica se ja foi curtido
        Optional<Curtida> curtida = curtidaRepository.findByPostAndUsuario(post, usuario);
        if (curtida.isEmpty()) {
            throw new RuntimeException("Curtida não existe");
        }

        //remove a curtida
        curtidaRepository.delete(curtida.get());

        //diminui as curtidas mas nao deixa negativo
        post.setLikesCount(Math.max(0, post.getLikesCount() - 1));
        postRepository.save(post);
    }
}
