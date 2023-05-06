package ir.tehranluster.paano.service;

import ir.tehranluster.paano.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends UserDetailsService {

    public Long save(UserDto userDto);

    public Long saveDetail(UserDto userDto);

    public UserDto findUserById(Long id);

    @Transactional(readOnly = true)
    UserDto findUserByEmail(String email);
}
