package vn.codegym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.model.SmartPhone;
import vn.codegym.repository.SmartPhoneRepository;

import java.util.List;

@Service
public class SmartPhoneServiceImpl implements SmartphoneService {
    @Autowired
    SmartPhoneRepository smartPhoneRepository;
    @Override
    public Iterable<SmartPhone> findAll() {
        return smartPhoneRepository.findAll();
    }

    @Override
    public SmartPhone findById(Integer id) {
        return smartPhoneRepository.findById(id).orElse(null);
    }

    @Override
    public SmartPhone save(SmartPhone phone) {
        return smartPhoneRepository.save(phone);
    }

    @Override
    public SmartPhone remove(Integer id) {
        SmartPhone smartPhone = smartPhoneRepository.findById(id).orElse(null);
        smartPhoneRepository.deleteById(id);
        return smartPhone;
    }
}
