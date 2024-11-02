package br.com.senac.sp.exemploSpring.controller;

import br.com.senac.sp.exemploSpring.dto.ProdutoDTO;
import br.com.senac.sp.exemploSpring.exception.EntityNotFoundException;
import br.com.senac.sp.exemploSpring.repository.ProdutoRepository;
import br.com.senac.sp.exemploSpring.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProdutoControllerTest {

    @Mock
    private ProdutoService service;

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductsReturnsListOfProducts() {
        List<ProdutoDTO> products = Arrays.asList(new ProdutoDTO("Product1", 10.0), new ProdutoDTO("Product2", 20.0));
        when(service.getAllProducts()).thenReturn(products);

        List<ProdutoDTO> result = controller.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Product1", result.get(0).getName());
    }

    @Test
    void getProductByIdReturnsProductWhenFound() {
        ProdutoDTO product = new ProdutoDTO("Product1", 10.0);
        when(service.getProductById(anyLong())).thenReturn(new ResponseEntity<>(product, HttpStatus.OK));

        ResponseEntity<ProdutoDTO> result = controller.getProductById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Product1", result.getBody().getName());
    }

    @Test
    void getProductByIdReturnsNotFoundWhenProductDoesNotExist() {
        when(service.getProductById(anyLong())).thenThrow(new EntityNotFoundException("Produto não encontrado"));

        assertThrows(EntityNotFoundException.class, () -> controller.getProductById(1L));
    }

    @Test
    void createProductReturnsCreatedProduct() {
        ProdutoDTO product = new ProdutoDTO("Product1", 10.0);
        when(service.saveProduct(any(ProdutoDTO.class))).thenReturn(product);

        ProdutoDTO result = controller.createProduct(product);

        assertEquals("Product1", result.getName());
    }

    @Test
    void updateProductReturnsUpdatedProduct() {
        ProdutoDTO product = new ProdutoDTO("UpdatedProduct", 15.0);
        when(service.updateProduct(anyLong(), any(ProdutoDTO.class))).thenReturn(new ResponseEntity<>(product, HttpStatus.OK));

        ResponseEntity<ProdutoDTO> result = controller.updateProduct(1L, product);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("UpdatedProduct", result.getBody().getName());
    }

    @Test
    void deleteProductReturnsNoContentWhenProductDeleted() {
        doNothing().when(repository).deleteById(anyLong());
        when(service.deleteProduct(anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        ResponseEntity<Void> result = controller.deleteProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}