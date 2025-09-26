package com.acn.springboot.catalog.app.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Producto Entity Tests")
class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto Test");
        producto.setCategoria("Categoria Test");
        producto.setPrecio(100.50);
        producto.setUrl("http://test.com/producto");
        producto.setSku("TEST-001");
    }

    @Test
    @DisplayName("Debe verificar getters y setters")
    void testGettersSetters() {
        assertEquals(1L, producto.getId());
        assertEquals("Producto Test", producto.getNombre());
        assertEquals("Categoria Test", producto.getCategoria());
        assertEquals(100.50, producto.getPrecio());
        assertEquals("http://test.com/producto", producto.getUrl());
        assertEquals("TEST-001", producto.getSku());
    }
}