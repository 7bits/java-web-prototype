package it.sevenbits.project.application.web.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * User related helper
 */
public final class UserResolver {

    /**
     * Provide User role
     * @return user role
     */
    public String getUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ((GrantedAuthority) (auth.getAuthorities().toArray()[0])).getAuthority();
    }

    /**
     * Check if current user is in certain role
     * @param roleName    Role to compare with
     * @return true if current user is in the same role, otherwise false
     */
    public boolean isUserInRole(final String roleName) {

        return this.getUserRole().equals(roleName);
    }

    /**
     * Checks if the user is not anonymous
     * @return true if the user is not anonymous
     */
    public boolean isAuthenticated() {

        return !"ROLE_ANONYMOUS".equals(this.getUserRole());
    }

    /**
     * Checks if current user has one of the roles
     * @param roleNames roles names array
     * @return true if current user is in one of the roles, otherwise false
     */
    public boolean isUserInRoles(final String[] roleNames) {

        boolean result = false;

        for (String roleName : roleNames) {
            if (isUserInRole(roleName)) {
                result = true;
            }
        }
        return result;
    }
}
