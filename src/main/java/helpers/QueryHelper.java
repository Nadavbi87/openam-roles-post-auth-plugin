package helpers;

public interface QueryHelper {
   String USER_QUERY_USERNAME_PARAMETER             = "username";
   String USER_QUERY_FIND_BY_USERNAME               =
           "Select u from User u where u.username = :" + USER_QUERY_USERNAME_PARAMETER;
   String USER_QUERY_FIND_BY_USERNAME_QUERY_NAME    = "User.findByUsername";
}
