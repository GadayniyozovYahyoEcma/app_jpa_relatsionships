package uz.pdp.app_jpa_relatsionships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String city;  // Tashkent

    @Column(nullable = false)
    private String district; // Mirobod

    @Column(nullable = false)
    private String street; // U.Nosir ko`chasi
}
