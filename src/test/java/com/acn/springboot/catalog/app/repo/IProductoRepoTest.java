package com.acn.springboot.catalog.app.repo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.acn.springboot.catalog.app.entity.Producto;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("IProductoRepo Tests")
class IProductoRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IProductoRepo productoRepo;

    private Producto producto1;
    private Producto producto2;
    private Producto producto3;

    @BeforeEach
    void setUp() {
        // Crear productos de prueba
        producto1 = new Producto();
        producto1.setNombre("Producto 1");
        producto1.setCategoria("Categoria A");
        producto1.setPrecio(100.0);
        producto1.setUrl("http://test.com/producto1");
        producto1.setSku("PROD-001");

        producto2 = new Producto();
        producto2.setNombre("Producto 2");
        producto2.setCategoria("Categoria B");
        producto2.setPrecio(200.0);
        producto2.setUrl("http://test.com/producto2");
        producto2.setSku("PROD-002");

        producto3 = new Producto();
        producto3.setNombre("Producto 3");
        producto3.setCategoria("Categoria A");
        producto3.setPrecio(300.0);
        producto3.setUrl("http://test.com/producto3");
        producto3.setSku("PROD-001");
        // Persistir los productos
        entityManager.persistAndFlush(producto1);
        entityManager.persistAndFlush(producto2);
        entityManager.persistAndFlush(producto3);
    }

    @Test
    @DisplayName("Debe encontrar todos los productos")
    void testFindAll() {
        // Act
        List<Producto> productos = productoRepo.findAll();

        // Assert
        assertEquals(3, productos.size());
    }
}