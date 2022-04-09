package uz.pdp.app_jpa_relatsionships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_jpa_relatsionships.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    boolean existsByName(String name);
}
