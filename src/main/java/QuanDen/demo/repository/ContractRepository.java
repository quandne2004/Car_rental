package QuanDen.demo.repository;

import QuanDen.demo.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {
}
