package br.uem.apoioarestaurante.controllers;

import br.uem.apoioarestaurante.metadata.entities.Estoque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Filipe Carvalho <filipekof97@gmail.com>
 */

public class TesteCaixaBrancaRelatorioProdutoFaltaController {
    
    private RelatorioProdutoFaltaController controller;
    
    public TesteCaixaBrancaRelatorioProdutoFaltaController() {
    }
    
    public boolean gerarRelatorio(){
        return ( controller.isManufatufado() || controller.isMateriaPrima() || controller.isRevenda() );
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        controller = new RelatorioProdutoFaltaController();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of inicializar method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testCaso1() {   
        controller.inicializar();
        controller.setMateriaPrima(true);
        assertEquals( true, gerarRelatorio());
              
    }   
    
    @Test
    public void testCaso2() {   
        controller.inicializar();
        controller.setMateriaPrima(true);
        assertEquals( true, gerarRelatorio());
            
    }    
    
    @Test
    public void testCaso3() {   
        controller.inicializar();
        controller.setRevenda(true);
        assertEquals( true, gerarRelatorio());
            
    }    
    
    @Test
    public void testCaso4() {   
        controller.inicializar();
        controller.setManufatufado(true);
        assertEquals( true, gerarRelatorio());
            
    }    
    
    @Test
    public void testCaso5() {   
        controller.inicializar();
        controller.setMateriaPrima(true);
        controller.setRevenda(true);
        controller.setManufatufado(true);
        assertEquals( true, gerarRelatorio());
            
    }       
    
    @Test
    public void testCaso6() {   
        controller.inicializar();
        controller.setMateriaPrima(true);
        controller.setRevenda(true);
        controller.setManufatufado(true);
        assertEquals( true, gerarRelatorio());
            
    }       
   
    
    
    
}
