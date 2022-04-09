package uz.pdp.app_jpa_relatsionships.payload;

import lombok.Data;

@Data
public class UniversityDto { // ma'lumotlarni tashish uchun xizmat qiladi
    private String name;
    private String city;
    private String district;
    private String street;
}
