package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.IAuthenticationUser;
import org.craftedsw.tripservicekata.user.User;


public class TripService {

	private final TripDAO tripDAO;
	private final User loggerUser;

	public TripService(TripDAO tripDAO, IAuthenticationUser authenticationUser) {
		this.tripDAO = tripDAO;
		this.loggerUser = authenticationUser.getLoggedUser();
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = loggerUser;
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = tripDAO.findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}


}
