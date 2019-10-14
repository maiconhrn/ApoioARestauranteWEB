package br.uem.apoioarestaurante.dao.generic.intf;

import java.util.List;

/**
 * @author Maicon
 */
public interface BasicGenericDAO<T> {
    boolean connect();

    T save(T t);

    T update(T t);

    boolean delete(T t);

    List<T> listAll();

    boolean disconnect();
}
