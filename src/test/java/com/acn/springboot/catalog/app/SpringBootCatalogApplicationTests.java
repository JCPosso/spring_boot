package com.acn.springboot.catalog.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import com.acn.springboot.catalog.app.controllers.IndexController;
import com.acn.springboot.catalog.app.dao.CatalogoDao;
import com.acn.springboot.catalog.app.dao.IProductoDao;
import com.acn.springboot.catalog.app.middleware.IMiddleware;
import com.acn.springboot.catalog.app.repo.IProductoRepo;
import com.acn.springboot.catalog.app.service.IServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Spring Boot Catalog Application Tests")
class SpringBootCatalogApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	@DisplayName("El contexto de Spring debe cargar correctamente")
	void contextLoads() {
		assertNotNull(applicationContext, "El contexto de aplicación debe cargarse");
	}

	@Test
	@DisplayName("Debe cargar todos los beans principales")
	void testPrincipalBeansLoaded() {
		assertNotNull(applicationContext.getBean(IndexController.class), 
			"IndexController debe estar disponible");
		assertNotNull(applicationContext.getBean(IProductoRepo.class), 
			"IProductoRepo debe estar disponible");
		assertNotNull(applicationContext.getBean(IMiddleware.class), 
			"IMiddleware debe estar disponible");
		assertNotNull(applicationContext.getBean(IServiceImpl.class), 
			"IServiceImpl debe estar disponible");
		assertNotNull(applicationContext.getBean(CatalogoDao.class), 
			"CatalogoDao debe estar disponible");
		assertNotNull(applicationContext.getBean(IProductoDao.class), 
			"IProductoDao debe estar disponible");
	}
}
