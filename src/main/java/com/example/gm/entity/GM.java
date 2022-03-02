package com.example.gm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GM {
    @Id
    @SequenceGenerator(
            name = "gm_id_sequence",
            sequenceName = "gm_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gm_id_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String compName;

    @ManyToOne(optional = false)
    private Director director;

    @OneToOne(optional = false, cascade = CascadeType.REMOVE)
    private Address address;

}
