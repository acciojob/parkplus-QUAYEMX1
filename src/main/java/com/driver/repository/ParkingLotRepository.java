package com.driver.repository;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer>{


    @Query(value = "select * from ParkingLot order by ",nativeQuery = true)
    List<Spot>findAllSpot();
}
