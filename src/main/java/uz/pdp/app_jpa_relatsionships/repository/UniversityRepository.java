package uz.pdp.app_jpa_relatsionships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.app_jpa_relatsionships.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
