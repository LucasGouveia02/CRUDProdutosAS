package br.com.senac.sp.exemploSpring.service;

import br.com.senac.sp.exemploSpring.dto.ProdutoDTO;
import br.com.senac.sp.exemploSpring.exception.EntityNotFoundException;
import br.com.senac.sp.exemploSpring.model.ProdutoModel;
import br.com.senac.sp.exemploSpring.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repo;

    public List<ProdutoDTO> getAllProducts() {
        return repo.findAll().stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }

    public ResponseEntity<ProdutoDTO> getProductById(Long id) {
        return new ResponseEntity<>(repo.findById(id).map(ProdutoDTO::new).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado")), HttpStatus.OK);
    }

    public ResponseEntity<ProdutoDTO> updateProduct(Long id, ProdutoDTO product) {
        ProdutoModel produto = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produto.setName(product.getName());
        produto.setPrice(product.getPrice());
        return new ResponseEntity<>(new ProdutoDTO(repo.save(produto)), HttpStatus.OK);
    }

    public ProdutoDTO saveProduct(ProdutoDTO product) {
        return new ProdutoDTO (repo.save(new ProdutoModel(product)));
    }

    public ResponseEntity<Void> deleteProduct(Long id) {
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
