package me.makise;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Menber {
    @Id
    private Long id;
    @Column(name="name". nullable = false)
    private String name;
}

