package com.lostAndFound.lostAndFound.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Lost extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String color;

    @NotBlank
    private String state;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description;

    private String picture;

    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate lostDate;

    @NotBlank
    private String lostPlace;

    private String proof;

    @NotBlank
    private String loserFullName;

    @Email
    @NotBlank
    private String loserEmail;

    private String loserIdentityDocument;


    // Relationship
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "lost", cascade = CascadeType.ALL)
    private List<LostFound> lostFoundList = new ArrayList<>();
    
    // Auto-generate unique code before persisting
    @PrePersist
    public void generateCode() {
        this.code = "LST-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}