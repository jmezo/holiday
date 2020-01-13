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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, length = 20)
    private String name;

    private String password;

    @ManyToOne
    @JoinColumn
    private User boss;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private AvailableHolidays availableHolidays;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }
}
