package uz.pdp.app_jpa_relatsionships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_jpa_relatsionships.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
