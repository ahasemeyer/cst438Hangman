package Hangman;
import Data.Users;
import Data.DBUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
/**
 *
 * @author swills
 */
public class UserCred {
    /**
     * Validate a given username password combination
     * Missing users are always invalid.
     * 
     * @param username
     * @param password
     * @param enabled toggle whether valid users must be enabled
     * @return true for valid user, false otherwise
     */
    public static boolean chkPass(String username, String password, boolean
            enabled) {
        boolean found = false;
        EntityManager em = DBUtil.getEM();
        TypedQuery<Users> q = em.createNamedQuery("Users.findByUsername",
            Users.class);
        Users user = null;
        String hashPass;
        /*
         * We must (try to) do the same amount of work regardless of whether or
         * not a valid user is found.
         */
        try {
            user = q.setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            System.out.println("Error chkPass " + e.getMessage());
        } finally {
            em.close();
        }
        try {
            if (user == null || (enabled && !user.getEnabled())) {
                hashPass = PasswordUtil.hashAndSaltPassword(password);
                // do the work of the comparison, but ignore the result
                found = password.equals(hashPass) && false;
            } else {
                hashPass = PasswordUtil.hashPassword(password, user.getSalt());
                found = user.getPassword().equals(hashPass);
            }
        } catch (Exception e) {
            System.out.println("Error chkPass " + e.getMessage());
        }
        return found;
    }
    
    /**
     * Validate a given username password combination
     * Missing users are always invalid.
     * Users must be enabled to be valid.
     * 
     * @param username
     * @param password
     * @return true for valid user, false otherwise
     */
    public static boolean chkPass(String username, String password) {
        return chkPass(username, password, true);
    }
}
