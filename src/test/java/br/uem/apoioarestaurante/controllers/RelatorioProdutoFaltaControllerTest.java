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

public class RelatorioProdutoFaltaControllerTest {
    
    private RelatorioProdutoFaltaController controller;
    
    public RelatorioProdutoFaltaControllerTest() {
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
    public void testInicializar() {   
        controller.inicializar();
        assertEquals( false, controller.isManufatufado());
        assertEquals( false, controller.isMateriaPrima());
        assertEquals( false, controller.isRevenda());
        
    }    
    
    /**
     * Test of getDataInicial method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testGetDataInicial() {
        Calendar myCal = Calendar.getInstance();
        myCal.set( 2020, 01, 12 );
        controller.setDataInicial(myCal.getTime());
        assertEquals( myCal.getTime(), controller.getDataInicial());
    }

    /**
     * Test of setDataInicial method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testSetDataInicial() {
        Calendar myCal = Calendar.getInstance();
        myCal.set( 2020, 01, 12 );
        controller.setDataInicial(myCal.getTime());
        assertEquals( myCal.getTime(), controller.getDataInicial());
    }

    /**
     * Test of getDataFinal method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testGetDataFinal() {
       Calendar myCal = Calendar.getInstance();
       myCal.set( 2020, 01, 12 );
       controller.setDataFinal(myCal.getTime());
       assertEquals( myCal.getTime(), controller.getDataFinal());
    }

    /**
     * Test of setDataFinal method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testSetDataFinal() {
       Calendar myCal = Calendar.getInstance();
       myCal.set( 2020, 01, 12 );
       controller.setDataFinal(myCal.getTime());
       assertEquals( myCal.getTime(), controller.getDataFinal());
    }

    /**
     * Test of getEstoque method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testGetEstoque() {
        Estoque estoque = new Estoque();
        estoque.setQtdEmEstoque(123);
        controller.setEstoque(estoque);
        assertTrue( controller.getEstoque().getQtdEmEstoque() == 123 );
    }

    /**
     * Test of setEstoque method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testSetEstoque() {
        Estoque estoque = new Estoque();
        estoque.setQtdEmEstoque(123);
        controller.setEstoque(estoque);
        assertTrue( controller.getEstoque().getQtdEmEstoque() == 123 );
    }

    /**
     * Test of getEstoques method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testGetEstoques() {
        Estoque estoque = new Estoque();
        estoque.setQtdEmEstoque(123);
        controller.getEstoques().add(estoque);        
        assertTrue( controller.getEstoques().get(0).getQtdEmEstoque() == 123 );
    }

    /**
     * Test of setEstoques method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testSetEstoques() {
        Estoque estoque = new Estoque();
        List<Estoque> estoques = new ArrayList<>();
        estoque.setQtdEmEstoque(123);
        estoques.add(estoque);
        controller.setEstoques(estoques);
        assertTrue( controller.getEstoques().get(0).getQtdEmEstoque() == 123 );
    }    

    /**
     * Test of isMateriaPrima method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testIsMateriaPrima() {
        controller.setMateriaPrima(true);
        assertEquals(true, controller.isMateriaPrima());
    }

    /**
     * Test of setMateriaPrima method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testSetMateriaPrima() {
        controller.setMateriaPrima(true);
        assertEquals(true, controller.isMateriaPrima());
    }

    /**
     * Test of isRevenda method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testIsRevenda() {
        controller.setRevenda(true);
        assertEquals(true, controller.isRevenda());
    }

    /**
     * Test of setRevenda method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testSetRevenda() {
        controller.setRevenda(true);
        assertEquals(true, controller.isRevenda());
    }

    /**
     * Test of isManufatufado method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testIsManufatufado() {
        controller.setManufatufado(true);
        assertEquals( true, controller.isManufatufado() );
    }

    /**
     * Test of setManufatufado method, of class RelatorioProdutoFaltaController.
     */
    @Test
    public void testSetManufatufado() {
        controller.setManufatufado(true);
        assertEquals( true, controller.isManufatufado() );
    }
    
}
