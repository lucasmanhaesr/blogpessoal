package br.com.blogpessoal.repository;

import br.com.blogpessoal.model.PostagemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {
}
