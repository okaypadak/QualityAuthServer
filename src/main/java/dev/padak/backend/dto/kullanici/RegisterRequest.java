package dev.padak.backend.dto.kullanici;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RegisterRequest {
    private String adi;
    private String soyadi;
    private String kullaniciAdi;
    private String eposta;
    private String sifre;
    private List<UserRolDTO> yetkiler;
}
