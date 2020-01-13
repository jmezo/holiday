package com.example.holiday.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private User user;

    private LocalDate startDate;

    private LocalDate endDate;

    private type type;

    private status status;

    public enum type {
        PTO, VTO, SICK
    }

    public enum status {
        approved, declined, pending
    }
}
