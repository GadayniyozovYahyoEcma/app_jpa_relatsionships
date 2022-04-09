package uz.pdp.app_jpa_relatsionships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_jpa_relatsionships.entity.Address;
import uz.pdp.app_jpa_relatsionships.entity.Group;
import uz.pdp.app_jpa_relatsionships.entity.Student;
import uz.pdp.app_jpa_relatsionships.entity.Subject;
import uz.pdp.app_jpa_relatsionships.payload.StudentDto;
import uz.pdp.app_jpa_relatsionships.repository.AddressRepository;
import uz.pdp.app_jpa_relatsionships.repository.GroupRepository;
import uz.pdp.app_jpa_relatsionships.repository.StudentRepository;
import uz.pdp.app_jpa_relatsionships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SubjectRepository subjectRepository;

    // 1).Ministry
    @GetMapping("/forMinistry")
    public Page<Student> getStudentPageForMinistry(@RequestParam int page){
        // 1-1=0         2-1=1        3-1=2       4-1=3
        // select * from student limit 10 offset (0*10);
        // select * from student limit 10 offset (1*10);
        // select * from student limit 10 offset (2*10);
        // select * from student limit 10 offset (3*10);

        Pageable pageable = PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    // 2).University
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentPageForUniversity(@PathVariable Integer universityId,@RequestParam int page){
        // 1-1=0         2-1=1        3-1=2       4-1=3
        // select * from student limit 10 offset (0*10);
        // select * from student limit 10 offset (1*10);
        // select * from student limit 10 offset (2*10);
        // select * from student limit 10 offset (3*10);

        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }

    // 3).Faculty dean's office
    @GetMapping("/forFaculty/{facultyId}")
    public Page<Student> getStudentPageForFaculty(@PathVariable Integer facultyId,@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_FacultyId(facultyId, pageable);
        return studentPage;
    }

    // 4).Group owner
    @RequestMapping("/forGroup/{id}")
    public Page<Student> getStudentPageForGroup(@PathVariable Integer id,@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> groupId = studentRepository.findAllByGroupId(id, pageable);
        return groupId;
    }



/*
    @GetMapping
    public List<Student> getStudent() {
        List<Student> studentsList = studentRepository.findAll();
        return studentsList;
    }

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
        if (!optionalAddress.isPresent()) {
            return "Address Not Found";
        }
        Address address = optionalAddress.get();
        student.setAddress(address);

        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()) {
            return "Group Not Found";
        }
            Group group = optionalGroup.get();
            student.setGroup(group);

        List<Subject> subjectList = subjectRepository.findAllById(studentDto.getSubjectIdList());
        if (!subjectList.isEmpty()) {
            student.setSubjects(subjectList);
            studentRepository.save(student);
            return "Student Added";
        }
        return "Student Not Found";

    }

 */
}
