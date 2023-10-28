package dev.padak.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class authenticationRequestDTO {

    private String adSoyad;
    private String kullaniciAdi;
    private String eposta;
    private String sifre;
    private String rol;
}
