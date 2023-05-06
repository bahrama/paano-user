package ir.tehranluster.paano.dto.addres;

import ir.tehranluster.paano.model.Addres;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface AddresDtoManager {

    AddresDto transferAddresToDto(Addres addres);

    @InheritInverseConfiguration
    Addres transferAddresDtoToEntity(AddresDto addresDto);
}
