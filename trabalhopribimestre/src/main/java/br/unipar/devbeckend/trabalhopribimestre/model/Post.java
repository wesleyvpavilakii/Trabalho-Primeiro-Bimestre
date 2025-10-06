package br.unipar.devbeckend.trabalhopribimestre.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity //vai virar tabela
@Table(name = "posts") //nome da tabela
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id automatico
    private Long id;

    @Column(length = 400) //posts c no maximo 400 caracteres
    private String conteudo;

    @ManyToOne(optional = false) //muitos posts para o mesmo autor
    @JoinColumn(name = "autor_id") //guarda o id do autor
    private Usuario autor;

    private LocalDateTime createdAt; //registra a data da criacao do post
    private Long likesCount = 0L; //contador de curtidas

    @Version
    private Long version; //controle de concorrencia, qnd todos varios mexem ao msm tempo

    public Post() {}

    public Post(Long id, String conteudo, Usuario autor) {
        this.id = id;
        this.conteudo = conteudo;
        this.autor = autor;
        this.likesCount = 0L;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now(); //data atual
        if (this.likesCount == null) this.likesCount = 0L; //garante q nunca é nulo
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getLikesCount() { return likesCount; }
    public void setLikesCount(Long likesCount) { this.likesCount = likesCount; }

    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}
