package br.com.senac.sp.exemploSpring.model;

import br.com.senac.sp.exemploSpring.dto.ProdutoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    public ProdutoModel(ProdutoDTO produtoDTO) {
        this.name = produtoDTO.getName();
        this.price = produtoDTO.getPrice();
    }
}
