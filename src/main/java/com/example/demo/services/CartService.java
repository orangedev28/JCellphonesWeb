package com.example.demo.services;

import com.example.demo.daos.Cart;
import com.example.demo.daos.Item;
import com.example.demo.entity.Brand;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Orders;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IOrdersRepository;
import com.example.demo.repository.IProductRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private static final String CART_SESSION_KEY = "cart";
    private final IOrdersRepository ordersRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IProductRepository productRepository;
    private final  UserService userService;
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
    public static Cart getCart(@NotNull HttpSession session) {
        return Optional.ofNullable((Cart)
                        session.getAttribute(CART_SESSION_KEY))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    session.setAttribute(CART_SESSION_KEY, cart);
                    return cart;
                });
    }
    public static void updateCart(@NotNull HttpSession session, Cart cart) {
        session.setAttribute(CART_SESSION_KEY, cart);
    }
    public void removeCart(@NotNull HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
    public int getSumQuantity(@NotNull HttpSession session) {
        return getCart(session).getCartItems().stream()
                .mapToInt(Item::getQuantity)
                .sum();
    }public double getSumPrice(@NotNull HttpSession session) {
        return getCart(session).getCartItems().stream()
                .mapToDouble(item -> item.getPrice() *
                        item.getQuantity())
                .sum();
    }
    public void saveCart(@NotNull HttpSession session) {
        var cart = getCart(session);
        if (cart.getCartItems().isEmpty()) return;
        var orders = new Orders();
        orders.setOrderDate(new Date(new Date().getTime()));
        orders.setPrice(getSumPrice(session));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }
        orders.setUser(userService.findByUsername(username));
        ordersRepository.save(orders);
        cart.getCartItems().forEach(item -> {
            var orderDetail = new OrderDetail();
            orderDetail.setOrders(orders);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setProduct(productRepository.findById(item.getId())
                    .orElseThrow());
            orderDetailRepository.save(orderDetail);
        });
        removeCart(session);
    }
}
