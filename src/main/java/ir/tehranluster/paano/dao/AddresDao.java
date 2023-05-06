package ir.tehranluster.paano.dao;

import ir.tehranluster.paano.model.Addres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresDao extends JpaRepository<Addres , Long> {

}
