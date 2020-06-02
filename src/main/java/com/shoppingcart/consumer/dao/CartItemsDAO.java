package com.shoppingcart.consumer.dao;

import com.shoppingcart.consumer.model.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemsDAO extends CrudRepository<CartItem, Integer> {}
