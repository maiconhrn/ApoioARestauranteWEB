package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.metadata.entities.*;
import br.uem.apoioarestaurante.metadata.types.OrderType;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertNotNull;

/**
 * @author Maicon
 */
public class OrderDAOTest {

    @Test
    public void seachByFilters() throws ParseException {
        OrderDAO orderDAO = OrderDAO.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        ClientDAO clientDAO = ClientDAO.getInstance();
        ProductDAO productDAO = ProductDAO.getInstance();

        User user = new User();
        user.setName("José Carlos Almirante");
        user.setCpf("10495058912");
        user.setLogin("jose");
        user.setPassword("123");
        user.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("16/06/1998"));

        userDAO.connect();
        userDAO.save(user);
        userDAO.disconnect();

        Client client = new Client();
        client.setName("Agostinho Carrara");
        client.setCpf("12365484452");
        client.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("12/01/1971"));
        client.setAddress("Rua Street, 2424");
        client.setPhone("4412345678");

        clientDAO.connect();
        clientDAO.save(client);
        clientDAO.disconnect();

        Product product1 = new Product();
        product1.setDescription("Coca cola 600ml");
        product1.setProductType("Revenda");
        product1.setSellPrice(4.5D);
        product1.setSupplier("Coca Cola Brasil");

        Product product2 = new Product();
        product2.setDescription("Baião de 2 250g");
        product2.setProductType("A la carte");
        product2.setSellPrice(35D);
        product2.setSupplier("Fabricação própria");

        productDAO.connect();
        productDAO.save(product1);
        productDAO.save(product2);
        productDAO.disconnect();

        OrderItem item1 = new OrderItem();
        item1.setProduct(product1);
        item1.setProductQtt(2);
        item1.setPrice(product1.getSellPrice() * item1.getProductQtt());

        OrderItem item2 = new OrderItem();
        item2.setProduct(product1);
        item2.setProductQtt(4);
        item2.setPrice(product1.getSellPrice() * item2.getProductQtt());

        OrderItem item3 = new OrderItem();
        item3.setProduct(product2);
        item3.setProductQtt(4);
        item3.setPrice(product2.getSellPrice() * item3.getProductQtt());

        Order order1 = new Order();
        order1.setType(OrderType.LOCAL);
        order1.setStatus("Aberto");
        order1.setUser(user);
        order1.setTable(23);
        order1.setItems(Arrays.asList(item1));
        item1.setOrder(order1);
        AtomicReference<Double> totalPrice1 = new AtomicReference<>(0D);
        order1.getItems().forEach(orderItem -> totalPrice1.updateAndGet(v -> v + orderItem.getPrice()));
        order1.setTotalPrice(totalPrice1.get());

        Order order2 = new Order();
        order2.setType(OrderType.DELIVERY);
        order2.setStatus("Fechado");
        order2.setUser(user);
        order2.setClient(client);
        order2.setItems(Arrays.asList(item2, item3));
        item2.setOrder(order2);
        item3.setOrder(order2);
        AtomicReference<Double> totalPrice2 = new AtomicReference<>(0D);
        order2.getItems().forEach(orderItem -> totalPrice2.updateAndGet(v -> v + orderItem.getPrice()));
        order2.setTotalPrice(totalPrice2.get());
        order2.setFinishDate(new Date());

        orderDAO.connect();
        orderDAO.save(order1);
        orderDAO.disconnect();

        orderDAO.connect();
        orderDAO.save(order2);
        orderDAO.disconnect();

        orderDAO.connect();
        List<Order> orders = orderDAO.findOrdersByFilters(OrderType.BOTH, true, 1L, false, 0, false, 0, false, 0);
        orderDAO.disconnect();
        assertNotNull(orders);
    }
}