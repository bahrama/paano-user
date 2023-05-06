package ir.tehranluster.paano.dao;

import ir.tehranluster.paano.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class CustomUserDaoImpl implements CustomUserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object[] findUserDtoById(Long id) {
        Query query = entityManager.createNativeQuery("select u.mobile as mobile , ud.name as name, ud.ssn as ssn, a.addr as addr, a.phone as phone, a.lat as lat, a.lng as lng from \"user\" u  inner join user_detail ud on u.id = ud.user_id " +
                " inner join addres a on a.id = ud.addres_id where u.id = :v_id");
        query.setParameter("v_id" , id);
            try {
                return (Object[]) query.getSingleResult();
            }catch (NoResultException noResultException){
                throw new EntityNotFoundException("not found from dao");
            }
    }

}
