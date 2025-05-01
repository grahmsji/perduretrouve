package com.lostAndFound.lostAndFound.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class LostFound extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "lost_id")
    private Lost lost;

    @ManyToOne
    @JoinColumn(name = "found_id")
    private Found found;

    private String status;
}
