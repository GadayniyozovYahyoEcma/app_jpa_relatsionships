package uz.pdp.app_jpa_relatsionships.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_jpa_relatsionships.entity.Address;
import uz.pdp.app_jpa_relatsionships.entity.Faculty;
import uz.pdp.app_jpa_relatsionships.entity.University;
import uz.pdp.app_jpa_relatsionships.payload.FacultyDto;
import uz.pdp.app_jpa_relatsionships.repository.FacultyRepository;
import uz.pdp.app_jpa_relatsionships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    // Vazirlik uchun
    @GetMapping
    public List<Faculty> getFaculty() {
        return facultyRepository.findAll();
    }

    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists)
            return "This university such faculty exist";

        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent())
            return "University not found";
        faculty.setUniversity(optionalUniversity.get());

        facultyRepository.save(faculty);
        return "Faculty saved";
    }

    // Universitet ma'sul xodimi uchun
    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesyByUniversityId(@PathVariable Integer universityId) {
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    @DeleteMapping(  "/faculty/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {
            facultyRepository.deleteById(id);
            return "Faculty deleted";
        }catch (Exception e){
            return "Error in deleting";
        }
    }

    @PutMapping(  "/faculty/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());
            Integer universityId = facultyDto.getUniversityId();
            Optional<University> optionalUniversity = universityRepository.findById(universityId);
            if (optionalUniversity.isPresent()) {
                University university = optionalUniversity.get();
                faculty.setUniversity(university);
                facultyRepository.save(faculty);

                return "Faculty edited";
            }
            else {
                return "University not found";
            }
        }
        else {
            return "Faculty not found";
        }
    }
}

