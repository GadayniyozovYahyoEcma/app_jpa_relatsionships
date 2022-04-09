package uz.pdp.app_jpa_relatsionships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_jpa_relatsionships.entity.Address;
import uz.pdp.app_jpa_relatsionships.payload.AddressDto;
import uz.pdp.app_jpa_relatsionships.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;


    @GetMapping
    public List<Address> getAddressLists(){
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    @PostMapping
    public String addAddress(@RequestBody AddressDto addressDto){
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setDistrict(addressDto.getDistrict());
        address.setStreet(addressDto.getStreet());

        addressRepository.save(address);
        return "Address added";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Integer id){
        addressRepository.deleteById(id);
        return "Address deleted";
    }

    @PutMapping("/address/{id}")
    public String editAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            Address address = optionalAddress.get();
            address.setCity(addressDto.getCity());
            address.setDistrict(addressDto.getDistrict());
            address.setStreet(addressDto.getStreet());

            addressRepository.save(address);
            return "Address edited";
        }
        return "Address not found";
    }
}
