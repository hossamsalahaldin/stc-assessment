package com.stc.demo.repositories;

import com.stc.demo.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    Item findByName(String name);
}
