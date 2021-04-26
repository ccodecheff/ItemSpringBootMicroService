package com.example.egen.item.repositatory;
import java.util.List;

import com.example.egen.item.entity.ItemOrderStore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderStoreRepository  extends JpaRepository<ItemOrderStore,Integer>{

    //void saveall(ItemOrderStore store);
     List<ItemOrderStore> findByOrderId(Integer orderId);
}
