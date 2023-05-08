package ir.tehranluster.paano.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.tehranluster.paano.utils.Provides;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
public class UserDto implements UserDetails {
    private long userId;
    @NotBlank(message = "Username cannot be blank")
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
    private String confirmPassword;
    private Date createDate;
    private Date updateDate;
    private Long userDetailId;
    private String name;
    private String ssn;
    private Long addresId;
    private String phone;
    private String addr;
    private double lat;
    private double lng;
    private Provides provider;
    private String message;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
