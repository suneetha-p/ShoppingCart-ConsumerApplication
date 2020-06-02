package com.shoppingcart.consumer.listener;

import com.shoppingcart.consumer.dao.CartItemsDAO;
import com.shoppingcart.consumer.model.CartItem;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Service
public class ConsumerListener {
    @Autowired
    private CartItemsDAO dao;
    private static final Logger logger= LogManager.getLogger(ConsumerListener.class);
    @KafkaListener(topics = "shoppingcart", groupId = "damacharla_sunitha_akar")
    public void consume(String message,Acknowledgment ack) throws Exception {
        System.out.println("message is:" + message);
        if (message != null) {
            Gson gson = new Gson();
            try {
                CartItem cartItem = gson.fromJson(message, CartItem.class);
                System.out.println("Aftrer converting to Json CartItem is: " + cartItem);
                CartItem cartItemFromDB = dao.save(cartItem);
                System.out.println("after inserting cartitems in DB :" + cartItem);
                if (cartItemFromDB != null) {
                    System.out.println("entered into if block");
                    ack.acknowledge();
                } else {
                    throw new Exception("CartItem is not added into Database");
                }
            } catch (Exception e){
                System.out.println("Exception message is:"+e);
            }
        }
    }
}