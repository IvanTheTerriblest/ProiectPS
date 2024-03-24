package com.projectPS.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table

public class Ingredients {
    @Id
    @SequenceGenerator(name="ingredients_sequence",
                        sequenceName = "ingredients_sequence",
                        allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ingredients_sequence"
    )
    private Long id;
    private String name;
    private LocalDate expirationDate;
    private Integer quantity;

}
