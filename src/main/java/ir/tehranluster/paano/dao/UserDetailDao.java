package ir.tehranluster.paano.dao;

import ir.tehranluster.paano.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetail,Long> {
}
