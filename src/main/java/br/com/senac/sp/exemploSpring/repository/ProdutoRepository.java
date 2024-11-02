package br.com.senac.sp.exemploSpring.repository;

import br.com.senac.sp.exemploSpring.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
}
