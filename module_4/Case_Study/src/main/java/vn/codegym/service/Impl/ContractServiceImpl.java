package vn.codegym.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.model.contract.Contract;
import vn.codegym.repository.ContractRepository;
import vn.codegym.service.ContractService;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractRepository contractRepository;


    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    @Override
    public Contract findById(int id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public void deleteById(int id) {
        contractRepository.deleteById(id);
    }

    @Override
    public Page<Contract> findAllByCustomer_Name(String search, Pageable pageable) {
        return contractRepository.findAllByCustomer_Name(search, pageable);
    }


}