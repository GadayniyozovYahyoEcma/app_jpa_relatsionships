package uz.pdp.app_jpa_relatsionships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_jpa_relatsionships.entity.Address;
import uz.pdp.app_jpa_relatsionships.entity.University;
import uz.pdp.app_jpa_relatsionships.payload.UniversityDto;
import uz.pdp.app_jpa_relatsionships.repository.AddressRepository;
import uz.pdp.app_jpa_relatsionships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {


    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;


    //READ
    @RequestMapping(value = "/university",method = RequestMethod.GET)
    public List<University> getUniversity(){
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    //CREATE
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto){
        // Yangi adres ochib oldik
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        // Yasab olgan Objectimizni DB ga saqladik va u bizga saqlagan Address ni berdi.
        Address savedAddress = addressRepository.save(address);

        //Yangi University yasab oldik
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);

        return "University added";
    }

    //CET University BY ID
    @RequestMapping(value = "/university/{id}",method = RequestMethod.GET)
    public University getUniversityById(@PathVariable Integer id){
        Optional<University> universityOptional = universityRepository.findById(id);
        if (universityOptional.isPresent()) {
            University universityId = universityOptional.get();
                return universityId;
        }
        return new University();
    }

    //DELETE University BY ID
    @RequestMapping(value = "/university/{id}",method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return "University deleted";
    }

    //UPDATE University By ID
    @RequestMapping(value = "/university/{id}",method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){
            University university = optionalUniversity.get();
            university.setName(universityDto.getName());

            // university ning hozirgi addressi
            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);

            universityRepository.save(university);
            return "University  edited";
        }
        return "University not found";
    }
}
