package vttp2022.assessment.csf.orderbackend.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.assessment.csf.orderbackend.Queries;
import vttp2022.assessment.csf.orderbackend.Utils;
import vttp2022.assessment.csf.orderbackend.models.Order;

@Repository
public class OrderRepository {
    private static final String TABLE_ORDERS = "orders";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //name, email, pizza_size, thick_crust, sauce, toppings, comments)
    public void createOrder(Order o){
        jdbcTemplate.update(Queries.INSERT_ORDER, o.getName(), o.getEmail(), o.getSize(), o.isThickCrust(), o.getSauce(), o.getToppings().toString(), o.getComments());
    }

    public List<Order> getOrdersByEmail(String email){
        SqlRowSet result = jdbcTemplate.queryForRowSet(Queries.GET_ORDER_BY_EMAIL, email);
        List<Order> orders = new LinkedList<>();
        while(result.next()){
            Order o = new Order();
            o.setName(result.getString("name"));
            o.setEmail(result.getString("email"));
            o.setOrderId(result.getInt("order_id"));
            o.setThickCrust(result.getBoolean("thick_crust"));
            o.setSauce(result.getString("sauce"));
            o.setSize(result.getInt("pizza_size"));
            o.setToppings(Utils.convertToppings(result.getString("toppings")));
            System.out.println(o);
            orders.add(o);
        }
        return orders;
    }

    


}
