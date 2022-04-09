package uz.pdp.app_jpa_relatsionships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_jpa_relatsionships.entity.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    boolean existsByNameAndUniversityId(String name, Integer university_id);

     List<Faculty> findAllByUniversityId(Integer university_id);
}
