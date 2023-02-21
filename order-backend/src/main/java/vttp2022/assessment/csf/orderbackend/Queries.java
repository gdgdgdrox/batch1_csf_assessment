package vttp2022.assessment.csf.orderbackend;

public class Queries {
    public static final String INSERT_ORDER = """
        INSERT INTO orders (name, email, pizza_size, thick_crust, sauce, toppings, comments) 
        VALUES (?,?,?,?,?,?,?);
            """;
    
    public static final String GET_ORDER_BY_EMAIL = """
        select order_id, name, email, pizza_size, thick_crust, sauce, toppings from orders where email=?;
            """;
}
