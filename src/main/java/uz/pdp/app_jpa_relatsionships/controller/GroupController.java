package uz.pdp.app_jpa_relatsionships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_jpa_relatsionships.entity.Faculty;
import uz.pdp.app_jpa_relatsionships.entity.Group;
import uz.pdp.app_jpa_relatsionships.payload.ApiResponse;
import uz.pdp.app_jpa_relatsionships.payload.GroupDto;
import uz.pdp.app_jpa_relatsionships.repository.FacultyRepository;
import uz.pdp.app_jpa_relatsionships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/group")

public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;


    //Vazirlik uchun
    //Read
    @GetMapping
    public List<Group> getGroups(){
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    // Universitet ma'sul xodimi uchun
    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId){
        List<Group> allByFaculty_university_id = groupRepository.findAllByFaculty_University_Id(universityId);
        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(universityId);
        List<Group> groupsByUniversityIdNative = groupRepository.getGroupsByUniversityIdNative(universityId);
        return allByFaculty_university_id;
    }


    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()){
            return "Such Faculty not found";
    }
        group.setFaculty(optionalFaculty.get());

        groupRepository.save(group);
        return "Group added";
    }

    @DeleteMapping("/group/{id}")
    public String deleteGroup(@PathVariable Integer id){
        groupRepository.findById(id);
        return "Group deleted";
    }

    @PutMapping("/group/{id}")
    public  String editGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto){
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()){
            Group group = optionalGroup.get();
            group.setName(groupDto.getName());

            Integer facultyId = groupDto.getFacultyId();
            Optional<Faculty> optionalFaculty = facultyRepository.findById(facultyId);
            if (optionalFaculty.isPresent()){
                Faculty faculty = optionalFaculty.get();
                group.setFaculty(faculty);
                groupRepository.save(group);
                return "Group edited";
            }else {
                return "Faculty not found";
            }
        }else {
            return "Group not found";
        }
    }
}
