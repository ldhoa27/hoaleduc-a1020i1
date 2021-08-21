package vn.codegym.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.model.contract.ContractDetail;
import vn.codegym.repository.ContractDetailRepository;
import vn.codegym.service.ContractDetailService;

import java.util.List;

@Service
public class ContractDetailServiceImpl implements ContractDetailService {
    @Autowired
    private ContractDetailRepository contractDetailRepository;

    @Override
    public ContractDetail findContractDetailsById(int id) {
        return contractDetailRepository.findContractDetailsByContractId(id);
    }

    @Override
    public ContractDetail findContractDetailsByIdContract(int id) {
        return contractDetailRepository.findContractDetailsByContractId(id);
    }

    @Override
    public void save(ContractDetail contractDetail) {
        contractDetailRepository.save(contractDetail);
    }
}