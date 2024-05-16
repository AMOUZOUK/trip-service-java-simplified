package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.IAuthenticationUser;
import org.craftedsw.tripservicekata.user.User;


public class TripService {

	private final TripDAO tripDAO;
	private final User loggedUser;

	public TripService(TripDAO tripDAO, IAuthenticationUser authenticationUser) {
		this.tripDAO = tripDAO;
		this.loggedUser = authenticationUser.getLoggedUser();
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		if( loggedUser == null ){
			throw new UserNotLoggedInException();
		}
		return user.isUserFriendLoggedUer(loggedUser) ? tripDAO.findTripsByUser(user)
				: new ArrayList<Trip>();
	}

}
