package br.com.senac.sp.exemploSpring.controller;


import br.com.senac.sp.exemploSpring.dto.ProdutoDTO;
import br.com.senac.sp.exemploSpring.model.ProdutoModel;
import br.com.senac.sp.exemploSpring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<ProdutoDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PostMapping
    public ProdutoDTO createProduct(@RequestBody ProdutoDTO product) {
        return service.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateProduct(@PathVariable Long id, @RequestBody ProdutoDTO productDetails) {
        return service.updateProduct(id, productDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return service.deleteProduct(id);
    }
}
