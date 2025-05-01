package com.lostAndFound.lostAndFound.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Column(unique = true)
    private String name;

    // Relationship
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Lost> lostList = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Found> foundList = new ArrayList<>();
}
