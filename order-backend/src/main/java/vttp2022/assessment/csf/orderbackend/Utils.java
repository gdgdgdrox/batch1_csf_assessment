package vttp2022.assessment.csf.orderbackend;

import java.util.Arrays;
import java.util.List;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;

public class Utils {
    public static JsonObject createResponse(String key, String message){
        return Json.createObjectBuilder().add(key, message).build();
    }

    public static List<String> convertToppings(String toppingsString){
        String[] topp = toppingsString.replace("[", "")
                                    .replace("]", "")
                                    .replace(" ", "")
                                    .split(",");

        List<String> toppings = Arrays.asList(topp);
        toppings.forEach(t -> System.out.println(t));
        return toppings;
    }

    public static JsonArray orderSummaryArray(List<OrderSummary> orderSummary){
        JsonArrayBuilder jab = Json.createArrayBuilder();
        orderSummary.forEach(os -> jab.add(Utils.orderSummaryObj(os)));
        return jab.build();
    }

    public static JsonObject orderSummaryObj(OrderSummary os){
        return Json.createObjectBuilder().add("order_id", os.getOrderId())
                                            .add("amount", os.getAmount())
                                            .build();
    }

    public static JsonObject createResponse(String key, JsonArray message){
        return Json.createObjectBuilder().add(key, message).build();
    }


}
