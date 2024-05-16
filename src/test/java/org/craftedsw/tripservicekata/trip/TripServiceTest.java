package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.AuthentificationUserImpl;
import org.craftedsw.tripservicekata.user.IAuthenticationUser;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @Mock
    private TripDAO tripDAO;

    User user;
    User loggerUser;

    @BeforeEach
    public void init(){
        user = new User();
        loggerUser = new User();
    }
    @Test
    public void test_userLogged(){
        IAuthenticationUser authen = new AuthentificationUserImpl(loggerUser);
        TripService tripService = new TripService(tripDAO, authen);
        Assertions.assertEquals(tripService.getTripsByUser(user), Collections.<Trip>emptyList());
    }
    @Test
    public void test_userNotLogged(){
        IAuthenticationUser authen = new AuthentificationUserImpl(null);
        TripService tripService = new TripService(tripDAO, authen);
        Assertions.assertThrows(UserNotLoggedInException.class, ()-> tripService.getTripsByUser(user));
    }
    @Test
    public void test_find_trip_when_user_and_logged_are_friend(){
        IAuthenticationUser authen = new AuthentificationUserImpl(loggerUser);
        Trip trip = new Trip();
        User user = new User();
        user.addTrip(trip);
        user.addFriend(loggerUser);
        List<Trip> list = new ArrayList<>();
        list.add(trip);
        Mockito.when(tripDAO.findTripsByUser(user)).thenReturn(list);
        TripService tripService = new TripService(tripDAO, authen);
        Assertions.assertEquals(tripService.getTripsByUser(user), user.trips());
    }

}
