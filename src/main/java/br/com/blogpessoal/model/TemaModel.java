package br.com.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "tb_tema")
public class TemaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "descricao do tema não pode ser nulo")
    private String descricao;

    //mappedBy = tema: chave estrangeira em postagem, fetchType = Lazy: carregamento da tabela tema primeiro depois postagem, cascadeType = remove: se remover tema também remove postagem
    @OneToMany(mappedBy = "tema", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    //JsonIgnoreProperties = tema: ignora a propriedade tema ao exigir a lista de postagens para não ter a redundancia infinita
    @JsonIgnoreProperties("tema")
    private List<PostagemModel> postagens;

    public TemaModel() {}
    public TemaModel(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PostagemModel> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<PostagemModel> postagens) {
        this.postagens = postagens;
    }
}
