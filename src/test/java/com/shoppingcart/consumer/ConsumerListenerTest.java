package com.shoppingcart.consumer;

import com.shoppingcart.consumer.dao.CartItemsDAO;
import com.shoppingcart.consumer.listener.ConsumerListener;
import com.shoppingcart.consumer.model.CartItem;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.support.Acknowledgment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerListenerTest {
    @InjectMocks
    ConsumerListener cl;
    @Mock
    private CartItemsDAO dao;
    @Mock
    private Acknowledgment ack;
    String message;
    CartItem cartItem;


    public void init(String message){
           this.message = message;
           Gson gson = new Gson();
           this.cartItem = gson.fromJson(message, CartItem.class);

    }
    @Test
    public void testConsumerWithAMessage() throws Exception{
        init("{itemName:LapTop,userID:john,itemQuantity:1}");
        when(dao.save(any())).thenReturn(cartItem);
        cl.consume(message,ack);
    }

    @Test(expected=Exception.class)
    public void testConsumerWithItemQuantityAsString() throws Exception{
        init("{itemName:SoundBar,userID:filip,itemQuantity:ten}");
        when(dao.save(any())).thenReturn(cartItem);
        cl.consume(message,ack);
    }
    @Test(expected=Exception.class)
    public void testConsumerWithoutDBConnection() throws Exception{
        init("{itemName:LapTop,userID:john,itemQuantity:1}");
        when(dao.save(any())).thenReturn(null);
         cl.consume(message,ack);
    }
}
