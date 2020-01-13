package com.example.holiday.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class AvailableHolidays {

    private static final int DEFAULT_COUNT = 20;
    private static final int DEFAULT_EXTRA = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn
    private User user;

    private int count = DEFAULT_COUNT;

    private int extra = DEFAULT_EXTRA;

    public AvailableHolidays(User user) {
        this.user = user;
    }
}
