package br.com.blogpessoal.repository;

import br.com.blogpessoal.model.TemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TemaRepository extends JpaRepository<TemaModel, Long> {
    List<TemaModel> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
