package uz.pdp.app_jpa_relatsionships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_jpa_relatsionships.entity.Subject;
import uz.pdp.app_jpa_relatsionships.payload.SubjectDto;
import uz.pdp.app_jpa_relatsionships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    //Create
    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exist";
        subjectRepository.save(subject);
         return "Subject added";
    }

    // Read
    @GetMapping
    public List<Subject> getSubjects(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    //Delete
    @DeleteMapping("/subject/{id}")
    public String deleteSubject(@PathVariable Integer id){
        subjectRepository.findById(id);
        return "Subject delete";
    }


    @PutMapping("/subject/{id}")
    public String editSubject(@PathVariable Integer id, @RequestBody SubjectDto subjectDto){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            Subject subject = optionalSubject.get();
            subject.setName(subjectDto.getName());

            subjectRepository.save(subject);
            return "Subject edited";
        }
        return "Subject not found";
    }

}
