package uz.pdp.app_jpa_relatsionships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.app_jpa_relatsionships.entity.Address;
import uz.pdp.app_jpa_relatsionships.entity.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAllByFaculty_University_Id(Integer faculty_university_id);

    @Query("select gr from groups gr where gr.faculty.university.id=: universityId")
    List<Group> getGroupsByUniversityId(Integer universityId);

    @Query(value = "select *\n" +
            "from groups g\n" +
            "         join faculty f on f.id = g.faculty_id\n" +
            "         join university u on u.id = f.university_id\n" +
            "where u.id=:universityId", nativeQuery = true)
    List<Group> getGroupsByUniversityIdNative(Integer universityId);
}
