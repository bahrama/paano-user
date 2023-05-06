package ir.tehranluster.paano.dao;

import javax.persistence.Tuple;

public interface CustomUserDao {
    Object[] findUserDtoById(Long id);

}
