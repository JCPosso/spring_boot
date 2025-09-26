package com.acn.springboot.catalog.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.acn.springboot.catalog.app.dao.ICatalogoDao;
import com.acn.springboot.catalog.app.dto.RequestCategoria;
import com.acn.springboot.catalog.app.dto.RequestProducto;
import com.acn.springboot.catalog.app.entity.CategoriaProducto;
import com.acn.springboot.catalog.app.entity.Producto;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DisplayName("ProductServiceImpl Tests")
class ProductServiceImplTest {

    @Mock
    private ICatalogoDao catalogodao;

    @InjectMocks
    private ProductServiceImpl productService;

    private RequestProducto requestProducto;
    private RequestCategoria requestCategoria;

    @BeforeEach
    void setUp() {
        requestCategoria = new RequestCategoria();
        requestCategoria.setNombre("Categoria Test");
        requestCategoria.setCodigounico("CAT-001");

        requestProducto = new RequestProducto();
        requestProducto.setNombre("Producto Test");
        requestProducto.setCategoria(requestCategoria);
        requestProducto.setPrecio(100.50);
        requestProducto.setSku("PROD-001");
        requestProducto.setUrl("http://test.com/producto");
    }

    @Test
    @DisplayName("Debe validar y convertir RequestProducto a Producto")
    void testValidaRequest() {
        // Act
        Producto resultado = productService.validaRequest(requestProducto);

        // Assert
        assertNotNull(resultado);
        assertEquals("Producto Test", resultado.getNombre());
        assertEquals("Categoria Test", resultado.getCategoria());
        assertEquals(100.50, resultado.getPrecio());
        assertEquals("PROD-001", resultado.getSku());
        assertEquals("http://test.com/producto", resultado.getUrl());
        assertNull(resultado.getId()); // ID debe ser nulo para nuevos productos
    }

    @Test
    @DisplayName("Debe guardar categoria correctamente")
    void testGuardarCategoria() {
        // Arrange
        doNothing().when(catalogodao).insertarCategoria(any(CategoriaProducto.class));

        // Act
        Producto resultado = productService.guardarCategoria(requestProducto);

        // Assert
        verify(catalogodao, times(1)).insertarCategoria(any(CategoriaProducto.class));
        assertNull(resultado); // El método retorna null según la implementación actual
    }
}