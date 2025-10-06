package br.unipar.devbeckend.trabalhopribimestre.repository;

import br.unipar.devbeckend.trabalhopribimestre.model.Curtida;
import br.unipar.devbeckend.trabalhopribimestre.model.Post;
import br.unipar.devbeckend.trabalhopribimestre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {
    Optional<Curtida> findByPostAndUsuario(Post post, Usuario usuario); //verifica se ja foi curtido
}
