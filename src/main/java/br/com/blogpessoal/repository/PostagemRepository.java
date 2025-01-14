package br.com.blogpessoal.repository;

import br.com.blogpessoal.model.PostagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {
    List<PostagemModel> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
