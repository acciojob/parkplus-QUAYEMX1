package com.driver.repository;

import com.driver.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer>{


    @Query(value = "select * from spot order by pricePerHour",nativeQuery = true)
    List<Spot>findAllSpot();

}
