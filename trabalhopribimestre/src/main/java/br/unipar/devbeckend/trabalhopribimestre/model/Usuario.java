package br.unipar.devbeckend.trabalhopribimestre.model;

import jakarta.persistence.*;

@Entity // vai virar uma tabela no banco
@Table(name = "usuarios") //nome estabelecido na tabela
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para gerar id automaticamente
    private Long id;

    @Column(nullable = false) //para deixar nome obrigatorio
    private String nome;

    @Column(nullable = false, unique = true) //username obrigatorio e unico
    private String username;

    public Usuario() {}

    public Usuario(Long id, String nome, String username) { //construtor e atributos
        this.id = id;
        this.nome = nome;
        this.username = username;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
