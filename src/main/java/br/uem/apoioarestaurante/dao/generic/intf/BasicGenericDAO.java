package br.uem.apoioarestaurante.dao.generic.intf;

import br.uem.apoioarestaurante.metadata.entities.BaseEntity;

import java.util.List;

/**
 * @author Maicon
 */
public interface BasicGenericDAO<T extends BaseEntity> {
    boolean connect();

    T findByID(String id);

    T save(T t);

    T update(T t);

    boolean delete(T t);

    List<T> listAll();

    boolean disconnect();
}
