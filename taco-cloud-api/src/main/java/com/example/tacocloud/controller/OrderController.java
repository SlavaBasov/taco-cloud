package com.example.tacocloud.controller;

import com.example.model.model.TacoOrder;
import com.example.model.model.Usr;
import com.example.model.repository.OrderRepository;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

  private final OrderRepository orderRepo;

  public OrderController(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }

  @GetMapping("/current")
  public String orderForm(){
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus,
      @AuthenticationPrincipal Usr user) {
    if(errors.hasErrors()) return "/orderForm";

    order.setUser(user);

    log.info("Order submitted: {}", order);
    orderRepo.save(order);
    sessionStatus.setComplete();
    return "completedOrder";
  }
}
