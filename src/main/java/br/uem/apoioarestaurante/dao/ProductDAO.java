package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.dao.generic.impl.HibernateBasicGenericDAO;
import br.uem.apoioarestaurante.metadata.entities.Product;

/**
 * @author Maicon
 */
public class ProductDAO extends HibernateBasicGenericDAO<Product> {

    private static ProductDAO ourInstance = new ProductDAO();

    public static ProductDAO getInstance() {
        return ourInstance;
    }

    private ProductDAO() {
        super(Product.class);
    }
}
