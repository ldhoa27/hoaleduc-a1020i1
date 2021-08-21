package vn.codegym.service;

import vn.codegym.model.employee.Position;

import java.util.List;

public interface PositionService {
    List<Position> findAll();

    Position findById(int id);
}
