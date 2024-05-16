package org.craftedsw.tripservicekata.user;

public class AuthentificationUserImpl implements IAuthenticationUser{

   private User loggerUser;
    public AuthentificationUserImpl(User loggerUser) {
        this.loggerUser = loggerUser;
    }
    @Override
    public User getLoggedUser() {
        return loggerUser;
    }
}
