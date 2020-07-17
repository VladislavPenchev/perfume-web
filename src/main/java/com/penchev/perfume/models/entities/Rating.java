package com.penchev.perfume.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ratings")
@EqualsAndHashCode(callSuper = true)
public class Rating extends BaseEntity {
    private int stars;
    private String opinion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
