package br.com.senac.sp.exemploSpring.dto;

import br.com.senac.sp.exemploSpring.model.ProdutoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String name;
    private double price;

    public ProdutoDTO(ProdutoModel produtoModel) {
        this.id = produtoModel.getId();
        this.name = produtoModel.getName();
        this.price = produtoModel.getPrice();
    }

    public ProdutoDTO (String name, double price) {
        this.name = name;
        this.price = price;
    }
}
