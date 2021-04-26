package com.example.egen.item.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.example.egen.item.entity.Item;
import com.example.egen.item.entity.ItemOrder;
import com.example.egen.item.entity.ItemOrderQty;
import com.example.egen.item.entity.ItemOrderResponse;
import com.example.egen.item.entity.ItemOrderStore;
import com.example.egen.item.entity.ItemIdQty;
import com.example.egen.item.entity.OrderAmount;
import com.example.egen.item.entity.OrderAmountEntity;
import com.example.egen.item.entity.ItemAmountResponse;
import com.example.egen.item.entity.ItemCustomer;
import com.example.egen.item.repositatory.ItemOrderStoreRepository;
import com.example.egen.item.repositatory.OrderAmountRepository;
import com.example.egen.item.service.ItemOrderStoreService;
import com.example.egen.item.service.ItemService;
import com.example.egen.item.service.OrderAmountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class ItemController {
    
    @Autowired
    private ItemService service;

    @Autowired
    private ItemOrderStoreRepository itemOrderStoreRepository;

    @Autowired
    private ItemOrderStoreService itemOrderStoreService;

    @Autowired
    private OrderAmountService orderService;
    
    private WebClient webClient;
    public ItemController(WebClient webClient){
        this.webClient=webClient;
    }


    private Double order_tax= 0.1;
    private Double shipping_charges= 4.0;
    private Double total=0.0;
    private Double sub_total=0.0;
   // private static final AtomicInteger count = new AtomicInteger(0); 
   

    @PostMapping("/addItem")
    public Item addItem(@RequestBody Item item){
        return service.saveItem(item);
    }

    @PostMapping(value="/selectItems", produces="application/json")
    public ItemAmountResponse selectItems(@RequestBody ItemCustomer data){
           
        ItemOrder itemOrder = webClient
        .post()
        .uri("http://localhost:9193/createOrder")
        .body(Mono.just(data), ItemCustomer.class)
        .retrieve().bodyToMono(ItemOrder.class).share().block();
       
        List<Item> itemList = new ArrayList<Item>();
        List<Integer> itemIds = new ArrayList<Integer>();
        List<ItemIdQty>itemIdQty = new ArrayList<ItemIdQty>();
        List<ItemOrderStore> itemOrderStoreList= new ArrayList<ItemOrderStore>();
        ItemOrderStore itemOrderStore= new ItemOrderStore(); 

        itemIdQty= itemOrder.getItems();
        
        
        for(ItemIdQty temp: itemIdQty){
            itemIds.add(temp.getItem_id());
            itemOrderStore= new ItemOrderStore(itemOrder.getOrder_id(), temp.getItem_id(), temp.getItem_qty());
            System.out.println(("order id :"+itemOrder.getOrder_id()+"Item ID:"+temp.getItem_id()));
            itemOrderStoreList.add(itemOrderStoreService.saveItem(itemOrderStore)); 
               
        }
        
        itemList= service.getItemByIds(itemIds);
   
        for(ItemOrderStore temp2: itemOrderStoreList){
            for(Item temp1: itemList){
                if(temp1.getItem_id()==temp2.getItemId()){

            sub_total+=(temp1.getItem_price()*temp2.getItemQty());
            System.out.println("Price:"+" "+temp1.getItem_price()+" "+temp2.getItemQty());}
        } }

        System.out.println("Subtotal:"+" "+sub_total);
        total= (sub_total+ sub_total*order_tax+shipping_charges); 
        System.out.println("total:"+" "+total);

        OrderAmount orderAmount= new OrderAmount(itemOrder.getOrder_id(),sub_total,order_tax,shipping_charges,total);  
       
        orderAmount= orderService.saveItem(orderAmount) ;    
        System.out.println("Order Id from Order Amount table:"+orderAmount.getOrderId());
         ItemAmountResponse itemAmountResponse= new ItemAmountResponse(itemOrder.getOrder_id(),total);
        
         return itemAmountResponse;
      
    }

    @GetMapping("/getOrderItem/{id}")
    public List<ItemOrderResponse> GetOrderItem(@PathVariable("id")  Integer orderId){
       
        List<Integer> itemIds= new ArrayList<>();
        List<ItemOrderStore> itemOrderStoreList= new ArrayList<>();
        List<ItemOrderResponse> itemOrderResponseList= new ArrayList<>();
        List<Item> itemList= new ArrayList<>();
        
        itemOrderStoreList= itemOrderStoreRepository.findByOrderId(orderId);
        for(ItemOrderStore temp: itemOrderStoreList){
            itemIds.add(temp.getItemId());
        }
       
        itemList= service.getItemByIds(itemIds);

        
        for(ItemOrderStore temp2: itemOrderStoreList){
            ItemOrderResponse itemOrderResponse= new ItemOrderResponse();
        for(Item temp1: itemList){
         

                itemOrderResponse.setItemId(temp2.getItemId());
                itemOrderResponse.setItemQty(temp2.getItemQty());
                itemOrderResponse.setItemName(temp1.getItem_name()); 
                
        }
        
        itemOrderResponseList.add(itemOrderResponse);

        }
         
        return itemOrderResponseList;
    }

    @PostMapping("/ItemByIds")
    public List <Item> getItemByIds(@RequestBody List<Integer> ids){
        return service.getItemByIds(ids);
    }

    
    @PutMapping("/updateOrderItem")
    public ItemAmountResponse updateOrderItem(@RequestBody ItemOrderQty item){
        if(item.getOrderId()!=null){
            List<ItemOrderStore> itemOrderStoreList= itemOrderStoreService.getItemByOrderId(item.getOrderId()); 
            ItemOrderStore itemOrderStore= new ItemOrderStore();
            List<Item> itemList = new ArrayList<Item>();
            List<Integer> itemIds = new ArrayList<Integer>();
          
            
           for(ItemOrderStore temp: itemOrderStoreList){
            
               itemIds.add(temp.getItemId());
            
            
                if (temp.getItemId().equals(item.getItemId())){
                    System.out.println("inside if statement");
                    itemOrderStore.setId(temp.getId());
                    itemOrderStore.setOrderId(temp.getOrderId());
                    itemOrderStore.setItemId(temp.getItemId());
                    itemOrderStore.setItemQty(item.getItemQty());
                   itemOrderStore=itemOrderStoreService.updateItem(itemOrderStore);
                    
               }
            
           }
        
            itemList= service.getItemByIds(itemIds);
            for(ItemOrderStore temp2: itemOrderStoreList){
                for(Item temp1: itemList){

            if(temp2.getItemId().equals(temp1.getItem_id()))

            {  System.out.println("Price:"+" "+temp1.getItem_price()+" "+temp2.getItemQty());
            sub_total+=(temp1.getItem_price()*temp2.getItemQty());
    }
          
        }
                 }
            System.out.println("Subtotal:"+" "+sub_total);
            total= (sub_total+ sub_total*order_tax+shipping_charges); 
            System.out.println("total:"+" "+total);
            Integer amountId= orderService.findAmountByOrderId(item.getOrderId()).getAmountId();
            OrderAmountEntity orderAmountEntity= new OrderAmountEntity(amountId,item.getOrderId(),sub_total,order_tax,shipping_charges,total);  
           
            OrderAmount orderAmount= orderService.updateItem(orderAmountEntity);    
            System.out.println("Success");
            ItemAmountResponse itemAmountResponse= new ItemAmountResponse(item.getOrderId(),total);
        
         return itemAmountResponse;
          
    
        } 
        ItemAmountResponse itemAmountResponse= new ItemAmountResponse(null,total);
        
        return itemAmountResponse;
               }

    
    @PutMapping("/updateItem")
    public Item updateItem(@RequestBody Item item){
        return service.updateItem(item);
    }

    @GetMapping("/getItems")
    public List<Item> getItems(){
        return service.getItems();
    }
    
    @DeleteMapping("/delete/{id}")
    public Integer deleteItem(@PathVariable int id){
        return service.deleteItemById(id);
    }
}
