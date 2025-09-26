package com.acn.springboot.catalog.app.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.acn.springboot.catalog.app.dao.CatalogoDao;
import com.acn.springboot.catalog.app.dao.IProductoDao;
import com.acn.springboot.catalog.app.entity.CategoriaProducto;
import com.acn.springboot.catalog.app.entity.Producto;
import com.acn.springboot.catalog.app.middleware.IMiddleware;
import com.acn.springboot.catalog.app.repo.IProductoRepo;
import com.acn.springboot.catalog.app.service.IServiceImpl;

@WebMvcTest(IndexController.class)
@ActiveProfiles("test")
@DisplayName("IndexController Tests")
class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductoRepo productrepo;

    @MockBean
    private IMiddleware md;

    @MockBean
    private IServiceImpl service;

    @MockBean
    private CatalogoDao catalogodao;

    @MockBean
    private IProductoDao productodao;

    private Producto producto1;
    private Producto producto2;
    private CategoriaProducto categoria;
    private final String VALID_TOKEN = "valid-token";
    private final String INVALID_TOKEN = "invalid-token";

    @BeforeEach
    void setUp() {
        producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Producto 1");
        producto1.setCategoria("Categoria A");
        producto1.setPrecio(100.0);
        producto1.setUrl("http://test.com/producto1");
        producto1.setSku("PROD-001");

        producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Producto 2");
        producto2.setCategoria("Categoria B");
        producto2.setPrecio(200.0);
        producto2.setUrl("http://test.com/producto2");
        producto2.setSku("PROD-002");

        categoria = new CategoriaProducto();
        categoria.setId(1L);
        categoria.setNombre("Categoria Test");
        categoria.setCodigounico("CAT-001");
    }

    @Test
    @DisplayName("GET /api/productos - Debe retornar productos con token válido")
    void testConsultaProductoConTokenValido() throws Exception {
        // Arrange
        List<Producto> productos = Arrays.asList(producto1, producto2);
        Page<Producto> productosPage = new PageImpl<>(productos);
        when(md.validateToken(VALID_TOKEN)).thenReturn(true);
        when(productrepo.findAll()).thenReturn(productos);
        when(productrepo.findAll(any(Pageable.class))).thenReturn(productosPage);
        doNothing().when(productodao).cargarInfoInicial();

        // Act & Assert
        mockMvc.perform(get("/api/productos")
                .header("token", VALID_TOKEN)
                .param("paginas", "0")
                .param("CantidadRegistrosxPagina", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producto").isArray())
                .andExpect(jsonPath("$.producto").isNotEmpty());
    }
}