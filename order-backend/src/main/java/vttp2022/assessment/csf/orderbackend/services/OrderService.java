package vttp2022.assessment.csf.orderbackend.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;
import vttp2022.assessment.csf.orderbackend.repositories.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private PricingService priceSvc;

	// POST /api/order
	// Create a new order by inserting into orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public void createOrder(Order order) {
		orderRepo.createOrder(order);

	}

	// GET /api/order/<email>/all
	// Get a list of orders for email from orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature

	/*
	 * 	private Integer orderId;
		private String name;
		private String email;
		private Float amount;
	 */
	public List<OrderSummary> getOrdersByEmail(String email) {
		List<Order> orders = orderRepo.getOrdersByEmail(email);
		// Use priceSvc to calculate the total cost of an order
		List<OrderSummary> orderSummary = new LinkedList<>();
		for (Order o : orders){
			OrderSummary os = new OrderSummary();
			os.setName(o.getName());
			os.setEmail(o.getEmail());
			os.setOrderId(o.getOrderId());
			Float amount = 0f;
			amount+=priceSvc.sauce(o.getSauce());
			amount+=priceSvc.size(o.getSize());
			amount+=o.isThickCrust() ? priceSvc.thickCrust() : priceSvc.thinCrust();
			for (String topping : o.getToppings()){
				amount += priceSvc.topping(topping);
			}
			os.setAmount(amount);
			System.out.println("ORDER SUMMARY " + os);
			orderSummary.add(os);
		}
		orderSummary.forEach(os -> System.out.println(os));
		return orderSummary;
	}



}
