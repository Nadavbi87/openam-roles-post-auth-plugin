package utils;

import com.iplanet.sso.SSOToken;
import com.sun.identity.idm.AMIdentity;
import com.sun.identity.idm.IdUtils;
import com.sun.identity.security.AdminTokenAction;
import com.sun.identity.shared.Constants;
import com.sun.identity.shared.debug.Debug;
import model.Role;
import repositories.impl.UserRepositoryImpl;
import services.UserService;
import services.impl.UserServiceImpl;

import java.security.AccessController;
import java.util.Set;

public class UserRolesPAPUtil {
    private static Debug debug = Debug.getInstance(UserRolesPAPUtil.class.getName());

    public static UserService getUserService(){
        return new UserServiceImpl(new UserRepositoryImpl());
    }
    public static String getUsernameFromSsoToken(SSOToken ssoToken){
        final String METHOD = "getUsernameFromSsoToken";
        String username = "";
        try {
            AMIdentity amIdentity = IdUtils.getIdentity(
                    AccessController.doPrivileged(AdminTokenAction.getInstance()),
                    ssoToken.getProperty(Constants.UNIVERSAL_IDENTIFIER));

            username = amIdentity.getName();
        }catch (Exception e){
            debug.error(METHOD, e);
        }

        return username;
    }

    public static String convertRolesSetToRolesIdString(Set<Role> roles){
        String result = null;
        if(roles != null && !roles.isEmpty()){
            result = joinRolesId(roles);
        }
        return result;
    }

    private static String joinRolesId(Set<Role> roles) {
        String result;
        String delimiter = ",";
        String prefix = "";
        result = "";
        for (Role role: roles ) {
            result += prefix + role.getId();
            prefix = delimiter;
        }
        return result;
    }
}
