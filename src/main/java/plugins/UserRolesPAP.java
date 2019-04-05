package plugins;

import com.iplanet.sso.SSOToken;
import com.sun.identity.authentication.spi.AMPostAuthProcessInterface;
import com.sun.identity.authentication.spi.AuthenticationException;
import com.sun.identity.shared.debug.Debug;
import model.User;
import services.UserService;
import utils.UserRolesPAPUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/***
 * Retrieves user roles and sets it as a session property during successful login,
 * writing to its debug log if the operation fails.
 */
public class UserRolesPAP implements AMPostAuthProcessInterface {
    private final static String ROLES_PROP_NAME = "Roles";
    private static Debug debug = Debug.getInstance(UserRolesPAP.class.getName());

    private UserService userService = UserRolesPAPUtil.getUserService();

    @Override
    public void onLoginSuccess(Map map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, SSOToken ssoToken) throws AuthenticationException {
        try {
            String username = UserRolesPAPUtil.getUsernameFromSsoToken(ssoToken);
            debug.message("User : " + username + " has been successfully logged in");
            User user = userService.findByUsername(username);

            String sessionRolesValue = UserRolesPAPUtil
                    .convertRolesSetToRolesIdString(user.getRoles());

            if(sessionRolesValue != null){
                debug.message("Add roles to user session.");
                ssoToken.setProperty(ROLES_PROP_NAME, sessionRolesValue);
            }
        }catch (Exception e){
            debug.error("Error while trying to get user roles", e);
        }
    }

    @Override
    public void onLoginFailure(Map map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {
        //Not used
    }

    @Override
    public void onLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, SSOToken ssoToken) throws AuthenticationException {
        //Not used
    }
}
