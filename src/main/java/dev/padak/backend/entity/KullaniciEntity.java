package dev.padak.backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kullanici")
public class KullaniciEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name ="ad_soyad")
    private String adSoyad;
    
    @Column(name ="kullanici_adi")
    private String kullaniciAdi;
    
    @Column(name ="eposta")
    private String eposta;
    
    @Column(name ="sifre")
    private String sifre;
    
    @Column(name ="rol")
    private String rol;
    
    
    
}
