package com.example.backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@SequenceGenerator(name = "users_id_seq", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    private Long id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "friendship",
            joinColumns = @JoinColumn(name = "requesting_user_id"),
            inverseJoinColumns = @JoinColumn(name = "receiving_user_id"))
    private List<User> friends;
}
