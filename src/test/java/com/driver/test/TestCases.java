package com.driver.test;

import com.driver.model.*;
import com.driver.repository.*;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ReservationService;
import com.driver.services.impl.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestCases {
    @Mock
    ParkingLotRepository parkingLotRepository1;

    @Mock
    SpotRepository spotRepository1;
    @Mock
    UserRepository userRepository4;
    @Mock
    UserRepository userRepository3;
    @Mock
    ParkingLotRepository parkingLotRepository3;
    @Mock
    SpotRepository spotRepository3;
    @Mock
    ReservationRepository reservationRepository3;
    @Mock
    ReservationRepository reservationRepository2;

    @Mock
    SpotRepository spotRepository2;
    @Mock
    PaymentRepository paymentRepository2;

    @InjectMocks
    PaymentServiceImpl paymentService;

    @InjectMocks
    UserServiceImpl userService;

    @InjectMocks
    ParkingLotServiceImpl parkingLotService;
    @InjectMocks
    ReservationServiceImpl reservationService;

    @Test
    public void testAddParkingLot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Test Parking Lot");
        parkingLot.setAddress("Test Address");
        parkingLot.setSpotList(new ArrayList<>());
        when(parkingLotRepository1.save(any())).thenReturn(parkingLot);
        ParkingLot result = parkingLotService.addParkingLot("Test Parking Lot", "Test Address");
        assertEquals(parkingLot.getName(), result.getName());
        assertEquals(parkingLot.getAddress(), result.getAddress());
        assertEquals(parkingLot.getSpotList().size(), result.getSpotList().size());
        verify(parkingLotRepository1, times(1)).save(any());

    }

    @Test
    public void testAddSpot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        parkingLot.setName("Test Parking Lot");
        parkingLot.setAddress("Test Address");
        parkingLot.setSpotList(new ArrayList<>());

        when(parkingLotRepository1.findById(any())).thenReturn(Optional.of(parkingLot));

        Spot spot = new Spot();
        spot.setOccupied(false);
        spot.setPricePerHour(5);
        spot.setReservationList(new ArrayList<>());
        spot.setSpotType(SpotType.TWO_WHEELER);
        spot.setParkingLot(parkingLot);
        List<Spot> spotList = new ArrayList<>();
        spotList.add(spot);
        parkingLot.setSpotList(spotList);

        when(parkingLotRepository1.save(any())).thenReturn(parkingLot);

        Spot result = parkingLotService.addSpot(1, 1, 5);
        assertEquals(spot.getOccupied(), result.getOccupied());
        assertEquals(spot.getSpotType(), result.getSpotType());
        assertEquals(spot.getPricePerHour(), result.getPricePerHour());
        assertEquals(spot.getReservationList().size(), result.getReservationList().size());
        assertEquals(spot.getParkingLot().getName(), result.getParkingLot().getName());
        assertEquals(spot.getParkingLot().getAddress(), result.getParkingLot().getAddress());
        assertEquals(spot.getParkingLot().getSpotList().size(), result.getParkingLot().getSpotList().size());
        verify(parkingLotRepository1, times(1)).findById(any());
        verify(parkingLotRepository1, times(0)).findAll();
        verify(spotRepository1, times(0)).findAll();
        verify(parkingLotRepository1, times(1)).save(any());
        verify(spotRepository1, times(0)).save(any());
    }

    @Test
    public void testAddSpot1() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        parkingLot.setName("Test Parking Lot");
        parkingLot.setAddress("Test Address");
        parkingLot.setSpotList(new ArrayList<>());

        when(parkingLotRepository1.findById(any())).thenReturn(Optional.of(parkingLot));

        Spot spot = new Spot();
        spot.setOccupied(false);
        spot.setPricePerHour(5);
        spot.setReservationList(new ArrayList<>());
        spot.setSpotType(SpotType.TWO_WHEELER);
        spot.setParkingLot(parkingLot);
        List<Spot> spotList = new ArrayList<>();
        spotList.add(spot);
        parkingLot.setSpotList(spotList);

        when(parkingLotRepository1.save(any())).thenReturn(parkingLot);

        Spot result = parkingLotService.addSpot(1, 1, 5);
        assertEquals(spot.getOccupied(), result.getOccupied());
        assertEquals(spot.getSpotType(), result.getSpotType());
        assertEquals(spot.getPricePerHour(), result.getPricePerHour());
        assertEquals(spot.getReservationList().size(), result.getReservationList().size());
        assertEquals(spot.getParkingLot().getName(), result.getParkingLot().getName());
        assertEquals(spot.getParkingLot().getAddress(), result.getParkingLot().getAddress());
        assertEquals(spot.getParkingLot().getSpotList().size(), result.getParkingLot().getSpotList().size());
        verify(parkingLotRepository1, times(1)).findById(any());
        verify(parkingLotRepository1, times(0)).findAll();
        verify(spotRepository1, times(0)).findAll();
        verify(parkingLotRepository1, times(1)).save(any());
        verify(spotRepository1, times(0)).save(any());
    }
    @Test
    public void testDeleteSpot() {
        doNothing().when(spotRepository1).deleteById(1);
        parkingLotService.deleteSpot(1);
        verify(spotRepository1, atMost(1)).deleteById(1);
        verify(parkingLotRepository1, atMost(1)).save(any());
        verify(parkingLotRepository1, times(0)).findAll();
        verify(spotRepository1, times(0)).findAll();
    }

    @Test
    public void testUpdateSpot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        parkingLot.setName("Test Parking Lot");
        parkingLot.setAddress("Test Address");
        parkingLot.setSpotList(new ArrayList<>());

        when(parkingLotRepository1.findById(any())).thenReturn(Optional.of(parkingLot));

        Spot spot = new Spot();
        spot.setId(1);
        spot.setOccupied(false);
        spot.setPricePerHour(5);
        spot.setReservationList(new ArrayList<>());
        spot.setSpotType(SpotType.TWO_WHEELER);
        spot.setParkingLot(parkingLot);
        List<Spot> spotList = new ArrayList<>();
        spotList.add(spot);
        parkingLot.setSpotList(spotList);

        when(spotRepository1.save(any())).thenReturn(spot);

        Spot result = parkingLotService.updateSpot(1, 1, 7);
        assertEquals(7, result.getPricePerHour());
        assertEquals(spot.getOccupied(), result.getOccupied());
        assertEquals(spot.getSpotType(), result.getSpotType());
        assertEquals(spot.getPricePerHour(), result.getPricePerHour());
        assertEquals(spot.getReservationList().size(), result.getReservationList().size());
        assertEquals(spot.getParkingLot().getName(), result.getParkingLot().getName());
        assertEquals(spot.getParkingLot().getAddress(), result.getParkingLot().getAddress());
        assertEquals(spot.getParkingLot().getSpotList().size(), result.getParkingLot().getSpotList().size());
        verify(parkingLotRepository1, times(1)).findById(any());
        verify(parkingLotRepository1, atMost(1)).save(any());
        verify(parkingLotRepository1, times(0)).findAll();
        verify(spotRepository1, times(0)).findAll();
        verify(spotRepository1, times(1)).save(any());
    }

    @Test
    public void testUpdateSpot1() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        parkingLot.setName("Test Parking Lot");
        parkingLot.setAddress("Test Address");
        parkingLot.setSpotList(new ArrayList<>());

        when(parkingLotRepository1.findById(any())).thenReturn(Optional.of(parkingLot));

        Spot spot = new Spot();
        spot.setId(1);
        spot.setOccupied(false);
        spot.setPricePerHour(5);
        spot.setReservationList(new ArrayList<>());
        spot.setSpotType(SpotType.TWO_WHEELER);
        spot.setParkingLot(parkingLot);
        List<Spot> spotList = new ArrayList<>();
        spotList.add(spot);
        parkingLot.setSpotList(spotList);

        when(spotRepository1.save(any())).thenReturn(spot);

        Spot result = parkingLotService.updateSpot(1, 1, 7);
        assertEquals(7, result.getPricePerHour());
        assertEquals(spot.getOccupied(), result.getOccupied());
        assertEquals(spot.getSpotType(), result.getSpotType());
        assertEquals(spot.getPricePerHour(), result.getPricePerHour());
        assertEquals(spot.getReservationList().size(), result.getReservationList().size());
        assertEquals(spot.getParkingLot().getName(), result.getParkingLot().getName());
        assertEquals(spot.getParkingLot().getAddress(), result.getParkingLot().getAddress());
        assertEquals(spot.getParkingLot().getSpotList().size(), result.getParkingLot().getSpotList().size());
        verify(parkingLotRepository1, times(1)).findById(any());
        verify(parkingLotRepository1, atMost(1)).save(any());
        verify(parkingLotRepository1, times(0)).findAll();
        verify(spotRepository1, times(0)).findAll();
        verify(spotRepository1, times(1)).save(any());
    }

    @Test
    public void testDeleteParkingLot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        parkingLot.setName("Test Parking Lot");
        parkingLot.setAddress("Test Address");
        parkingLot.setSpotList(new ArrayList<>());
        when(parkingLotRepository1.save(any())).thenReturn(parkingLot);
        ParkingLot savedParkingLot = parkingLotRepository1.save(parkingLot);
        parkingLotService.deleteParkingLot(savedParkingLot.getId());
        verify(parkingLotRepository1, times(1)).deleteById(savedParkingLot.getId());
        verify(parkingLotRepository1, times(0)).findAll();
    }

    @Test
    public void deleteUser_validInput_userDeleted() {
        Integer userId = 1;
        doNothing().when(userRepository4).deleteById(userId);
        userService.deleteUser(userId);
        verify(userRepository4, times(1)).deleteById(userId);
        verify(userRepository4, times(0)).findAll();
    }

    @Test
    public void updatePassword_validInput_passwordUpdated() {
        Integer userId = 1;
        String newPassword = "newPassword";
        User user = new User();
        user.setId(userId);
        user.setPassword("oldPassword");
        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setPassword(newPassword);
        when(userRepository4.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository4.save(any())).thenReturn(updatedUser);
        User returnedUser = userService.updatePassword(userId, newPassword);
        assertEquals(newPassword, returnedUser.getPassword());
        verify(userRepository4, times(1)).save(user);
        verify(userRepository4, times(0)).findAll();
        verify(userRepository4, times(1)).findById(any());
    }

    @Test
    public void register_validInput_userCreated() {
        String name = "John Doe";
        String phoneNumber = "1234567890";
        String password = "password";
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setReservationList(new ArrayList<>());
        when(userRepository4.save(any())).thenReturn(user);
        userService.register(name, phoneNumber, password);
        verify(userRepository4, times(1)).save(any());
        verify(userRepository4, times(0)).findAll();
        verify(userRepository4, times(0)).findById(any());
    }

    @Test
    public void testReserveSpotSuccess() throws Exception {
        User user = new User();
        user.setId(1);
        user.setReservationList(new ArrayList<>());
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        Spot spot = new Spot();
        spot.setId(1);
        spot.setOccupied(false);
        spot.setPricePerHour(10);
        spot.setSpotType(SpotType.TWO_WHEELER);
        spot.setReservationList(new ArrayList<>());
        Spot spot1 = new Spot();
        spot1.setId(2);
        spot1.setOccupied(true);
        spot1.setPricePerHour(1);
        spot1.setSpotType(SpotType.OTHERS);
        spot1.setReservationList(new ArrayList<>());
        Spot spot2 = new Spot();
        spot2.setId(3);
        spot2.setOccupied(false);
        spot2.setPricePerHour(5);
        spot2.setSpotType(SpotType.FOUR_WHEELER);
        spot2.setReservationList(new ArrayList<>());
        Spot spot3 = new Spot();
        spot3.setId(4);
        spot3.setOccupied(false);
        spot3.setPricePerHour(3);
        spot3.setSpotType(SpotType.OTHERS);
        spot3.setReservationList(new ArrayList<>());
        List<Spot> spotList = new ArrayList<>();
        spotList.add(spot);
        spotList.add(spot1);
        spotList.add(spot2);
        spotList.add(spot3);
        parkingLot.setSpotList(spotList);
        when(userRepository3.findById(any())).thenReturn(Optional.of(user));
        when(parkingLotRepository3.findById(any())).thenReturn(Optional.of(parkingLot));
        Reservation reservation = reservationService.reserveSpot(1, 1, 2, 3);
        assertNotNull(reservation);
        assertEquals(reservation.getUser(), user);
        assertEquals(reservation.getSpot(), spot3);
        assertEquals(reservation.getNumberOfHours(), 2);
        assertTrue(!spot.getOccupied());
        assertTrue(spot1.getOccupied());
        assertTrue(!spot2.getOccupied());
        assertTrue(spot3.getOccupied());
        verify(userRepository3, times(1)).save(any());
        verify(spotRepository3, times(1)).save(any());
        verify(reservationRepository3, times(0)).save(any());
        verify(reservationRepository3, times(0)).findAll();
        verify(spotRepository3, times(0)).findAll();
        verify(userRepository3, times(0)).findAll();
        verify(parkingLotRepository3, times(0)).findAll();
        verify(userRepository3, atMost(2)).findById(any());
        verify(parkingLotRepository3, atMost(2)).findById(any());
    }

    @Test
    public void testReserveSpotSuccess1() throws Exception {
        User user = new User();
        user.setId(1);
        user.setReservationList(new ArrayList<>());
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        Spot spot = new Spot();
        spot.setId(1);
        spot.setOccupied(false);
        spot.setPricePerHour(10);
        spot.setSpotType(SpotType.TWO_WHEELER);
        spot.setReservationList(new ArrayList<>());
        Spot spot1 = new Spot();
        spot1.setId(2);
        spot1.setOccupied(true);
        spot1.setPricePerHour(1);
        spot1.setSpotType(SpotType.OTHERS);
        spot1.setReservationList(new ArrayList<>());
        Spot spot2 = new Spot();
        spot2.setId(3);
        spot2.setOccupied(false);
        spot2.setPricePerHour(5);
        spot2.setSpotType(SpotType.FOUR_WHEELER);
        spot2.setReservationList(new ArrayList<>());
        Spot spot3 = new Spot();
        spot3.setId(4);
        spot3.setOccupied(false);
        spot3.setPricePerHour(3);
        spot3.setSpotType(SpotType.OTHERS);
        spot3.setReservationList(new ArrayList<>());
        List<Spot> spotList = new ArrayList<>();
        spotList.add(spot);
        spotList.add(spot1);
        spotList.add(spot2);
        spotList.add(spot3);
        parkingLot.setSpotList(spotList);
        when(userRepository3.findById(any())).thenReturn(Optional.of(user));
        when(parkingLotRepository3.findById(any())).thenReturn(Optional.of(parkingLot));
        Reservation reservation = reservationService.reserveSpot(1, 1, 2, 3);
        assertNotNull(reservation);
        assertEquals(reservation.getUser(), user);
        assertEquals(reservation.getSpot(), spot3);
        assertEquals(reservation.getNumberOfHours(), 2);
        assertTrue(!spot.getOccupied());
        assertTrue(spot1.getOccupied());
        assertTrue(!spot2.getOccupied());
        assertTrue(spot3.getOccupied());
        verify(userRepository3, times(1)).save(any());
        verify(spotRepository3, times(1)).save(any());
        verify(reservationRepository3, times(0)).save(any());
        verify(reservationRepository3, times(0)).findAll();
        verify(spotRepository3, times(0)).findAll();
        verify(userRepository3, times(0)).findAll();
        verify(parkingLotRepository3, times(0)).findAll();
        verify(userRepository3, atMost(2)).findById(any());
        verify(parkingLotRepository3, atMost(2)).findById(any());
    }

    @Test
    public void testReserveSpotUserNotFound() throws Exception {
        User user = new User();
        user.setId(1);
        user.setReservationList(new ArrayList<>());
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        Spot spot = new Spot();
        spot.setId(1);
        spot.setOccupied(false);
        spot.setPricePerHour(10);
        spot.setSpotType(SpotType.TWO_WHEELER);
        spot.setReservationList(new ArrayList<>());
        Spot spot1 = new Spot();
        spot1.setId(2);
        spot1.setOccupied(true);
        spot1.setPricePerHour(1);
        spot1.setSpotType(SpotType.OTHERS);
        spot1.setReservationList(new ArrayList<>());
        Spot spot2 = new Spot();
        spot2.setId(3);
        spot2.setOccupied(false);
        spot2.setPricePerHour(5);
        spot2.setSpotType(SpotType.FOUR_WHEELER);
        spot2.setReservationList(new ArrayList<>());
        Spot spot3 = new Spot();
        spot3.setId(4);
        spot3.setOccupied(false);
        spot3.setPricePerHour(3);
        spot3.setSpotType(SpotType.OTHERS);
        spot3.setReservationList(new ArrayList<>());
        List<Spot> spotList = new ArrayList<>();
        spotList.add(spot);
        spotList.add(spot1);
        spotList.add(spot2);
        spotList.add(spot3);
        parkingLot.setSpotList(spotList);
        when(userRepository3.findById(any())).thenReturn(null);
        when(parkingLotRepository3.findById(any())).thenReturn(Optional.of(parkingLot));
        try {
            Reservation reservation = reservationService.reserveSpot(1, 1, 2, 3);
        } catch(Exception e){
            assertEquals(e.getMessage(), "Cannot make reservation");
            verify(reservationRepository3, times(0)).findAll();
            verify(spotRepository3, times(0)).findAll();
            verify(userRepository3, times(0)).findAll();
            verify(parkingLotRepository3, times(0)).findAll();
            verify(userRepository3, atMost(2)).findById(any());
            verify(parkingLotRepository3, atMost(2)).findById(any());
        }
    }

    @Test
    public void testReserveSpotParkingLotNotFound() throws Exception {
        User user = new User();
        user.setId(1);
        user.setReservationList(new ArrayList<>());
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        Spot spot = new Spot();
        spot.setId(1);
        spot.setOccupied(false);
        spot.setPricePerHour(10);
        spot.setSpotType(SpotType.TWO_WHEELER);
        spot.setReservationList(new ArrayList<>());
        Spot spot1 = new Spot();
        spot1.setId(2);
        spot1.setOccupied(true);
        spot1.setPricePerHour(1);
        spot1.setSpotType(SpotType.OTHERS);
        spot1.setReservationList(new ArrayList<>());
        Spot spot2 = new Spot();
        spot2.setId(3);
        spot2.setOccupied(false);
        spot2.setPricePerHour(5);
        spot2.setSpotType(SpotType.FOUR_WHEELER);
        spot2.setReservationList(new ArrayList<>());
        Spot spot3 = new Spot();
        spot3.setId(4);
        spot3.setOccupied(false);
        spot3.setPricePerHour(3);
        spot3.setSpotType(SpotType.OTHERS);
        spot3.setReservationList(new ArrayList<>());
        List<Spot> spotList = new ArrayList<>();
        spotList.add(spot);
        spotList.add(spot1);
        spotList.add(spot2);
        spotList.add(spot3);
        parkingLot.setSpotList(spotList);
        when(userRepository3.findById(any())).thenReturn(Optional.of(user));
        when(parkingLotRepository3.findById(any())).thenReturn(null);
        try {
            Reservation reservation = reservationService.reserveSpot(1, 1, 2, 3);
        } catch(Exception e){
            assertEquals(e.getMessage(), "Cannot make reservation");
            verify(reservationRepository3, times(0)).findAll();
            verify(spotRepository3, times(0)).findAll();
            verify(userRepository3, times(0)).findAll();
            verify(parkingLotRepository3, times(0)).findAll();
            verify(userRepository3, atMost(2)).findById(any());
            verify(parkingLotRepository3, atMost(2)).findById(any());
        }
    }

    @Test
    public void testReserveSpotParkingLotNotFound1() throws Exception {
        User user = new User();
        user.setId(1);
        user.setReservationList(new ArrayList<>());
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        Spot spot = new Spot();
        spot.setId(1);
        spot.setOccupied(false);
        spot.setPricePerHour(10);
        spot.setSpotType(SpotType.TWO_WHEELER);
        spot.setReservationList(new ArrayList<>());
        Spot spot1 = new Spot();
        spot1.setId(2);
        spot1.setOccupied(true);
        spot1.setPricePerHour(1);
        spot1.setSpotType(SpotType.OTHERS);
        spot1.setReservationList(new ArrayList<>());
        Spot spot2 = new Spot();
        spot2.setId(3);
        spot2.setOccupied(false);
        spot2.setPricePerHour(5);
        spot2.setSpotType(SpotType.FOUR_WHEELER);
        spot2.setReservationList(new ArrayList<>());
        Spot spot3 = new Spot();
        spot3.setId(4);
        spot3.setOccupied(false);
        spot3.setPricePerHour(3);
        spot3.setSpotType(SpotType.OTHERS);
        spot3.setReservationList(new ArrayList<>());
        List<Spot> spotList = new ArrayList<>();
        spotList.add(spot);
        spotList.add(spot1);
        spotList.add(spot2);
        spotList.add(spot3);
        parkingLot.setSpotList(spotList);
        when(userRepository3.findById(any())).thenReturn(Optional.of(user));
        when(parkingLotRepository3.findById(any())).thenReturn(null);
        try {
            Reservation reservation = reservationService.reserveSpot(1, 1, 2, 3);
        } catch(Exception e){
            assertEquals(e.getMessage(), "Cannot make reservation");
            verify(reservationRepository3, times(0)).findAll();
            verify(spotRepository3, times(0)).findAll();
            verify(userRepository3, times(0)).findAll();
            verify(parkingLotRepository3, times(0)).findAll();
            verify(userRepository3, atMost(2)).findById(any());
            verify(parkingLotRepository3, atMost(2)).findById(any());
        }
    }

    @Test
    public void testReserveSpotNoSpotFound() throws Exception {
        User user = new User();
        user.setId(1);
        user.setReservationList(new ArrayList<>());
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        Spot spot = new Spot();
        spot.setId(1);
        spot.setOccupied(false);
        spot.setPricePerHour(10);
        spot.setSpotType(SpotType.TWO_WHEELER);
        spot.setReservationList(new ArrayList<>());
        Spot spot1 = new Spot();
        spot1.setId(2);
        spot1.setOccupied(true);
        spot1.setPricePerHour(1);
        spot1.setSpotType(SpotType.TWO_WHEELER);
        spot1.setReservationList(new ArrayList<>());
        Spot spot2 = new Spot();
        spot2.setId(3);
        spot2.setOccupied(false);
        spot2.setPricePerHour(5);
        spot2.setSpotType(SpotType.TWO_WHEELER);
        spot2.setReservationList(new ArrayList<>());
        Spot spot3 = new Spot();
        spot3.setId(4);
        spot3.setOccupied(false);
        spot3.setPricePerHour(3);
        spot3.setSpotType(SpotType.TWO_WHEELER);
        spot3.setReservationList(new ArrayList<>());
        List<Spot> spotList = new ArrayList<>();
        spotList.add(spot);
        spotList.add(spot1);
        spotList.add(spot2);
        spotList.add(spot3);
        parkingLot.setSpotList(spotList);
        when(userRepository3.findById(any())).thenReturn(Optional.of(user));
        when(parkingLotRepository3.findById(any())).thenReturn(Optional.of(parkingLot));
        try {
            Reservation reservation = reservationService.reserveSpot(1, 1, 2, 3);
        } catch(Exception e){
            assertEquals(e.getMessage(), "Cannot make reservation");
            verify(reservationRepository3, times(0)).findAll();
            verify(spotRepository3, times(0)).findAll();
            verify(userRepository3, times(0)).findAll();
            verify(parkingLotRepository3, times(0)).findAll();
            verify(userRepository3, atMost(2)).findById(any());
            verify(parkingLotRepository3, atMost(2)).findById(any());
        }
    }
    @Test
    public void pay_validInput_success() throws Exception {
        Reservation reservation = new Reservation();
        Spot spot = new Spot();
        spot.setPricePerHour(10);
        reservation.setNumberOfHours(2);
        reservation.setSpot(spot);
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation);
        spot.setReservationList(reservationList);
        when(reservationRepository2.findById(any())).thenReturn(Optional.of(reservation));
        Payment payment = paymentService.pay(1, 20, "caSh");
        assertEquals(true, payment.isPaymentCompleted());
        assertEquals(PaymentMode.CASH, payment.getPaymentMode());
        verify(paymentRepository2, times(0)).save(any());
        verify(reservationRepository2, times(1)).save(any());
        verify(spotRepository2, atMost(1)).save(any());
        verify(reservationRepository2, times(0)).findAll();
        verify(spotRepository2, times(0)).findAll();
        verify(reservationRepository2, times(0)).findAll();
        verify(paymentRepository2, times(0)).findAll();
    }
    @Test
    public void pay_validInput_success1() throws Exception {
        Reservation reservation = new Reservation();
        Spot spot = new Spot();
        spot.setPricePerHour(10);
        reservation.setNumberOfHours(2);
        reservation.setSpot(spot);
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation);
        spot.setReservationList(reservationList);
        when(reservationRepository2.findById(any())).thenReturn(Optional.of(reservation));
        Payment payment = paymentService.pay(1, 20, "caSh");
        assertEquals(true, payment.isPaymentCompleted());
        assertEquals(PaymentMode.CASH, payment.getPaymentMode());
        verify(paymentRepository2, times(0)).save(any());
        verify(reservationRepository2, times(1)).save(any());
        verify(spotRepository2, atMost(1)).save(any());
        verify(reservationRepository2, times(0)).findAll();
        verify(spotRepository2, times(0)).findAll();
        verify(reservationRepository2, times(0)).findAll();
        verify(paymentRepository2, times(0)).findAll();
    }

    @Test
    public void pay_insufficientAmount_throwsException() throws Exception {
        Reservation reservation = new Reservation();
        Spot spot = new Spot();
        spot.setPricePerHour(10);
        reservation.setNumberOfHours(2);
        reservation.setSpot(spot);
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation);
        spot.setReservationList(reservationList);
        when(reservationRepository2.findById(any())).thenReturn(Optional.of(reservation));
        try {
            paymentService.pay(1, 15, "cash");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Insufficient Amount");
            verify(reservationRepository2, times(0)).findAll();
            verify(spotRepository2, times(0)).findAll();
            verify(reservationRepository2, times(0)).findAll();
            verify(paymentRepository2, times(0)).findAll();
        }
    }

    @Test
    public void pay_invalidPaymentMode_throwsException() throws Exception {
        Reservation reservation = new Reservation();
        Spot spot = new Spot();
        spot.setPricePerHour(10);
        reservation.setNumberOfHours(2);
        reservation.setSpot(spot);
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation);
        spot.setReservationList(reservationList);
        when(reservationRepository2.findById(any())).thenReturn(Optional.of(reservation));
        try {
            paymentService.pay(1, 25, "upip");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Payment mode not detected");
            verify(reservationRepository2, times(0)).findAll();
            verify(spotRepository2, times(0)).findAll();
            verify(reservationRepository2, times(0)).findAll();
            verify(paymentRepository2, times(0)).findAll();
        }
    }
}