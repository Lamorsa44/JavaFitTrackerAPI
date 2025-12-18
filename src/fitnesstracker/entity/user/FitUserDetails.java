package fitnesstracker.entity.user;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class FitUserDetails implements UserDetails {
    final private FitUser fitUser;

    public FitUserDetails(FitUser fitUser) {
        this.fitUser = fitUser;
    }

    public long getId() {
        return fitUser.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return fitUser.getPassword();
    }

    @Override
    public String getUsername() {
        return fitUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
