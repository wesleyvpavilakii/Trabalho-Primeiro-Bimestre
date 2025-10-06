package br.unipar.devbeckend.trabalhopribimestre.repository;

import br.unipar.devbeckend.trabalhopribimestre.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc(); //posts mais novos
    List<Post> findAllByOrderByLikesCountDesc(); //posts com mais likes
}
