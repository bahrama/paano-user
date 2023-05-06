package ir.tehranluster.paano.dto.user;

import ir.tehranluster.paano.model.Addres;
import ir.tehranluster.paano.model.User;
import ir.tehranluster.paano.model.UserDetail;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface UserDtoManager {

    @Mapping(source = "user.id" , target = "userId")
    @Mapping(source = "user.email" , target = "email")
    @Mapping(source = "user.password" , target = "password")
    @Mapping(source = "user.createDate" , target = "createDate")
    @Mapping(source = "user.updateDate" , target = "updateDate")
    @Mapping(source = "userDetail.id" , target = "userDetailId")
    @Mapping(source = "userDetail.name" , target = "name")
    @Mapping(source = "userDetail.ssn" , target = "ssn")
    @Mapping(source = "addres.id" , target = "addresId")
    @Mapping(source = "addres.phone" , target = "phone")
    @Mapping(source = "addres.addr" , target = "addr")
    @Mapping(source = "addres.lat" , target = "lat")
    @Mapping(source = "addres.lng" , target = "lng")
    UserDto transferUserToDto(User user, UserDetail userDetail, Addres addres);

    @Mapping(source = "userId" , target = "id")
    @Mapping(source = "email" , target = "email")
    @Mapping(source = "password" , target = "password")
    @Mapping(source = "createDate" , target = "createDate")
    @Mapping(source = "updateDate" , target = "updateDate")
    User transferUserDtoToEntity(UserDto userDto);

    @Mapping(source = "userDetailId" , target = "id")
    @Mapping(source = "name" , target = "name")
    @Mapping(source = "ssn" , target = "ssn")
    @Mapping(source = "addresId" , target = "addres.id")
    @Mapping(source = "userId" , target = "user.id")
    UserDetail transferUserDetailDtoToEntity(UserDto userDto);

    @Mapping(source = "addresId" , target = "id")
    @Mapping(source = "addr" , target = "addr")
    @Mapping(source = "phone" , target = "phone")
    @Mapping(source = "lat" , target = "lat")
    @Mapping(source = "lng" , target = "lng")
    Addres transferAddresDtoToEntity(UserDto userDto);


}
