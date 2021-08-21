package vn.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.model.contract.Contract;

import java.util.List;

public interface ContractService {
    Page<Contract> findAll(Pageable pageable);
    Contract findById(int id);
    void save(Contract contract);
    void deleteById(int id);

    Page<Contract> findAllByCustomer_Name(String search, Pageable pageable);
}