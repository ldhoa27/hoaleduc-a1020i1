package com.backend.airline_tickets_agency_management.model.service.destination;

import com.backend.airline_tickets_agency_management.model.dto.destination.ScenicDto;
import com.backend.airline_tickets_agency_management.model.entity.destinations_scenic.Scenic;
import com.backend.airline_tickets_agency_management.model.repository.destination.IScenicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class ScenicServiceImpl implements IScenicService {
    @Autowired
    private IScenicRepository scenicRepository;

    @Override
    public Map<String, Object> save(ScenicDto scenicDto, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        List<String> errors = new ArrayList<>();
        try {
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(ob -> {
                    errors.add(ob.getDefaultMessage());
                });
                result.put("status", false);
                result.put("msg", "SAVE FAILED");
                result.put("errors", errors);
                return result;
            }

        } catch (Exception e) {
            result.put("status", false);
            result.put("msg", "SAVE FAILED");
        }

        Scenic scenic = new Scenic();
        scenic.setScenicName(scenicDto.getScenicName().trim());
        scenic.setScenicDescription(scenicDto.getScenicDescription().trim());
        scenic.setScenicImage(scenicDto.getScenicImage());
        scenic.setDestination(scenicDto.getDestination());
        scenic.setFlag(1);
        scenicRepository.save(scenic);

        result.put("status", true);
        result.put("msg", "Tạo bài viết thành công!!!");
        return result;
    }

    @Override
    public Map<String, Object> update(ScenicDto scenicDto, BindingResult bindingResult) {
        Scenic scenic = scenicRepository.findById(scenicDto.getScenicId()).orElse(null);
        Map<String, Object> result = new HashMap<>();
        List<String> errors = new ArrayList<>();
        try {
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(ob -> {
                    errors.add(ob.getDefaultMessage());
                });
                result.put("status", false);
                result.put("msg", "UPDATE FAILED");
                result.put("errors", errors);
                return result;
            }

        } catch (Exception e) {
            result.put("status", false);
            result.put("msg", "UPDATE FAILED");
        }

        scenic.setScenicId(scenicDto.getScenicId());
        scenic.setScenicName(scenicDto.getScenicName().trim());
        scenic.setScenicDescription(scenicDto.getScenicDescription().trim());
        scenic.setScenicImage(scenicDto.getScenicImage());
        scenic.setDestination(scenicDto.getDestination());
        scenic.setFlag(1);
        scenicRepository.save(scenic);

        result.put("status", true);
        result.put("msg", "Cập nhật bài viết thành công!!!");
        return result;
    }

    @Override
    public Iterable<Scenic> getScenicByDestination(Long idDestination) {
        return scenicRepository.findScenicByDestination(idDestination);
    }

    @Override
    public void delScenic(Long id) {
        scenicRepository.deleteById(id);
    }

    @Override
    public Optional<Scenic> findById(Long id) {
        return scenicRepository.findById(id);
    }

}
