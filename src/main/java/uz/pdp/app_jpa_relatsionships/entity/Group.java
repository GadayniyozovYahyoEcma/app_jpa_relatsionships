package uz.pdp.app_jpa_relatsionships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")   // Database ga table qilishi uchun
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID ni sequences qilishi uchun
    private Integer id;


    @Column(nullable = false)
    private String name;

    @ManyToOne // Many Group To One faculty *** ko'plab guruhlarga bitta fakultet tegishli bo'ladi
    private Faculty faculty;

//    @OneToMany  // One group To Many students *** Bitta guruhga ko'plab studentlar
//    private List<Student> students;


}
