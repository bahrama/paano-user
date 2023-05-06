package ir.tehranluster.paano.service;

import ir.tehranluster.paano.dao.AddresDao;
import ir.tehranluster.paano.dao.UserDao;
import ir.tehranluster.paano.dao.UserDetailDao;
import ir.tehranluster.paano.dto.user.UserDto;
import ir.tehranluster.paano.dto.user.UserDtoManager;
import ir.tehranluster.paano.exceptions.EntityNotFoundException;
import ir.tehranluster.paano.model.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final UserDetailDao userDetailDao;
    private final AddresDao addresDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDtoManager userDtoManager = Mappers.getMapper(UserDtoManager.class);

    @Transactional(propagation = Propagation.REQUIRED , isolation = Isolation.READ_COMMITTED)
    public Long saveAddres(UserDto userDto) {
        Long addresId = addresDao.save(userDtoManager.transferAddresDtoToEntity(userDto)).getId();
        userDto.setAddresId(addresId);
        return addresId;
    }
    @Transactional(propagation = Propagation.REQUIRED , isolation = Isolation.READ_COMMITTED)
    public Long saveUserDetail(UserDto userDto){
        Long userDetailId = userDetailDao.save(userDtoManager.transferUserDetailDtoToEntity(userDto)).getId();
        userDto.setUserDetailId(userDetailId);
        return userDetailId;
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED , isolation = Isolation.READ_COMMITTED)
    public Long save(UserDto userDto){
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        Long userId = userDao.save(userDtoManager.transferUserDtoToEntity(userDto)).getId();
        return userId;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED , isolation = Isolation.READ_COMMITTED)
    public Long saveDetail(UserDto userDto) {
        saveAddres(userDto);
        Long userId = userDao.save(userDtoManager.transferUserDtoToEntity(userDto)).getId();
        userDto.setUserId(userId);
        saveUserDetail(userDto);
        return userId;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findUserById(Long id) {
       Object[] userDtoValue = userDao.findUserDtoById(id);
       UserDto userDto = new UserDto();
       userDto.setEmail((String) userDtoValue[0]);
       userDto.setName((String) userDtoValue[1]);
       userDto.setSsn((String) userDtoValue[2]);
       userDto.setAddr((String) userDtoValue[3]);
       userDto.setPhone((String) userDtoValue[4]);
       userDto.setLat((Double) userDtoValue[5]);
       userDto.setLng((Double) userDtoValue[6]);
       return userDto;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findUserByEmail(String email) {
      UserDto userDto = new UserDto();
      Optional<User> user = userDao.findUserByEmail(email);
      if(user.isPresent()){
          userDto = userDtoManager.transferUserToDto(user.get(),null,null);
          return userDto;
      }
      else{
          throw new EntityNotFoundException("user not found");
      }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByEmail(username);
    }
}
