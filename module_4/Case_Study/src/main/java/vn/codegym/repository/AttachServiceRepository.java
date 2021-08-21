package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.model.service.AttachService;

public interface AttachServiceRepository extends JpaRepository<AttachService, Integer> {
}
