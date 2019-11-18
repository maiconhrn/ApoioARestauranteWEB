package br.uem.apoioarestaurante.views;

import br.uem.apoioarestaurante.controllers.PedidoController;
import br.uem.apoioarestaurante.controllers.UsuarioController;
import br.uem.apoioarestaurante.metadata.entities.Pedido;
import br.uem.apoioarestaurante.metadata.entities.Usuario;
import br.uem.apoioarestaurante.metadata.types.PedidoTipo;
import br.uem.apoioarestaurante.models.PedidoModel;
import br.uem.apoioarestaurante.utils.FacesUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Universidade Estadual de Maringá
 *
 * Autor: José Gabriel Júnior       Ra: 54011
 */

@Named
@SessionScoped
public class FecharPedidoView implements Serializable {

    @Inject
    private PedidoView pedidoView;

    @Inject
    private PedidoController pedidoController;

    @Inject
    private UsuarioController usuarioController;

    @Inject
    private PedidoModel pedidoModel;

    private Date minDate;

    private List<Usuario> usuarios;

    private Pedido pedido;

    private void initForCloseOrder(String id) {
        usuarios = usuarioController.findAllUser();
        pedido = pedidoController.findById(Long.parseLong(id));

    }

    @PostConstruct
    public void init() {
        String closeType = FacesUtil.getParam("type");
        String closing = FacesUtil.getParam("closing");
        String orderId = FacesUtil.getParam("id");
        initForCloseOrder(orderId);
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoTipo[] getTypes() {
        List<PedidoTipo> tipos = new ArrayList<>(Arrays.asList(PedidoTipo.values()));

        tipos.remove(PedidoTipo.BOTH);

        return tipos.toArray(new PedidoTipo[0]);
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public PedidoView getPedidoView() {
        return pedidoView;
    }

    public void setPedidoView(PedidoView pedidoView) {
        this.pedidoView = pedidoView;
    }

    public PedidoController getPedidoController() {
        return pedidoController;
    }

    public void setPedidoController(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public PedidoModel getPedidoModel() {
        return pedidoModel;
    }

    public void setPedidoModel(PedidoModel pedidoModel) {
        this.pedidoModel = pedidoModel;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
