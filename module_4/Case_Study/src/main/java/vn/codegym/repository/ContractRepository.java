package vn.codegym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.model.contract.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Page<Contract> findAll(Pageable pageable);
    @Query("SELECT e FROM Contract e WHERE e.customer.name LIKE ?1")
    Page<Contract> findAllByCustomer_Name(String name, Pageable pageable);
}
