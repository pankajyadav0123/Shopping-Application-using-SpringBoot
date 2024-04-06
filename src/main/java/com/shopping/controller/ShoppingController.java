package com.shopping.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.model.Inventory;
import com.shopping.model.Order;
import com.shopping.model.Payment;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/shoppingview")
public class ShoppingController {

 private int currentOrdered = 0;
 private int availableItems = 100;
 private final int price = 100;

 private Map<String, Integer> coupons = new HashMap<>();

 @PostConstruct
 public void initialize() {
     coupons.put("OFF5", 5);
     coupons.put("OFF10", 10);
 }

 @GetMapping("/inventory")
 public ResponseEntity<Inventory> getInventory() {
     return ResponseEntity.ok(new Inventory(currentOrdered, price, availableItems));
 }

 @GetMapping("/fetchCoupons")
 public ResponseEntity<Map<String, Integer>> fetchCoupons() {
     return ResponseEntity.ok(coupons);
 }

 @PostMapping("/{userId}/order")
 public ResponseEntity<Object> placeOrder(@PathVariable int userId, @RequestParam int qty, @RequestParam String coupon) {
     // Check quantity
     if (qty < 1 || qty > availableItems) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("description", "Invalid quantity"));
     }

     // Check coupon
     if (!coupons.containsKey(coupon)) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("description", "Invalid coupon"));
     }

     // Calculate amount
     int discount = coupons.get(coupon);
     int amount = (int) (qty * price * (1 - discount / 100.0));

     // Update inventory
     currentOrdered += qty;
     availableItems -= qty;

     // Create order
     Order order = new Order(100, userId, qty, amount, coupon);

     return ResponseEntity.ok(order);
 }

 @PostMapping("/{userId}/{orderId}/pay")
 public ResponseEntity<Payment> makePayment(@PathVariable int userId, @PathVariable int orderId, @RequestParam int amount) {
     // Mock payment API response
     Random random = new Random();
     int statusCode = random.nextInt(6);
     Payment response;

     switch (statusCode) {
         case 0:
             response = new Payment(userId, orderId, "tran010100001", "successful", null);
             break;
         case 1:
             response = new Payment(userId, orderId, "tran010100002", "failed", "Payment Failed as amount is invalid");
             break;
         case 2:
             response = new Payment(userId, orderId, "tran010100003", "failed", "Payment Failed from bank");
             break;
         case 3:
             response = new Payment(userId, orderId, "tran010100004", "failed", "Payment Failed due to invalid order id");
             break;
         case 4:
             response = new Payment(userId, orderId, "tran010100005", "failed", "No response from payment server");
             break;
         case 5:
             response = new Payment(userId, orderId, "tran010100006", "failed", "Order is already paid for");
             break;
         default:
             response = null;
     }

     return ResponseEntity.status(statusCode == 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
 }

 @GetMapping("/{userId}/orders")
 public ResponseEntity<List<Order>> getOrders(@PathVariable int userId) {
     // Return list of orders for given user
     return ResponseEntity.ok(Collections.emptyList()); // Mocked response
 }

 @GetMapping("/{userId}/orders/{orderId}")
 public ResponseEntity<Object> getOrder(@PathVariable int userId, @PathVariable int orderId) {
     // Return order details if found, else return 404
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("orderId", orderId));
 }
}
