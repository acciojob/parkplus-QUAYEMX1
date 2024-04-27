package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.driver.model.SpotType.*;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {

        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);

       parkingLot=parkingLotRepository1.save(parkingLot);

       return parkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {

        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();

        SpotType spotType=null;
        if(numberOfWheels==2){
            spotType= TWO_WHEELER;
        }else if(numberOfWheels==4){
            spotType= FOUR_WHEELER;
        }else{
            spotType=OTHERS;
        }

        Spot spot=new Spot();
        spot.setSpotType(spotType);
        spot.setPricePerHour(pricePerHour);
        spot.setOccupied(Boolean.FALSE);
        spot.setParkingLot(parkingLot);

        parkingLot.getSpotList().add(spot);
//        parkingLot.setSpotList(parkingLot.getSpotList());
//        List<Spot>parkingLotList=parkingLot.getSpotList();
//        parkingLotList.add(spot);
//        parkingLot.setSpotList(parkingLotList);

       spot=spotRepository1.save(spot);

        return spot;
    }

    @Override
    public void deleteSpot(int spotId) {

       spotRepository1.deleteById(spotId);

    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {

        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();

        if(parkingLot==null){
            return null;
        }
        Spot spot=spotRepository1.findById(spotId).get();

        if(spot==null){
            return null;
        }

        spot.setParkingLot(parkingLot);
        spot.setPricePerHour(pricePerHour);

        parkingLot.getSpotList().add(spot);

        spot=spotRepository1.save(spot);


        return spot;
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {

        parkingLotRepository1.deleteById(parkingLotId);
    }
}
