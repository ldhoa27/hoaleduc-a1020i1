package vn.codegym.service;

import vn.codegym.model.contract.ContractDetail;

import java.util.List;

public interface ContractDetailService {
    ContractDetail findContractDetailsById(int id);
    ContractDetail findContractDetailsByIdContract(int id);
    void save(ContractDetail contractDetail);
}