package com.example.SpringLearnH2db.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SpringLearnH2db.Entitys.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    // @Query("Select e from ProductEntity e where e.title =:title and e.price = :price")
    // List<ProductEntity> findByTitleOrderByPrice(String string);

    List<ProductEntity> findByOrderByPrice();

}
