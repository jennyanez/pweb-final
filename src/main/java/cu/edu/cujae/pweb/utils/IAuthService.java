package cu.edu.cujae.pweb.utils;

import cu.edu.cujae.pweb.dto.UserAuthenticatedDto;

public interface IAuthService {

    UserAuthenticatedDto login(String username, String password);

}
