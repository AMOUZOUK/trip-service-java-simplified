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
		List<Trip> tripList = new ArrayList<Trip>();
		boolean isFriend = false;
		if (loggedUser != null) {
			if (user.isUserFriendLoggedUer(loggedUser)) {
				tripList = tripDAO.findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}


}
