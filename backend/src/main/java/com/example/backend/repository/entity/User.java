package com.example.backend.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = "friends")
@Getter
@NoArgsConstructor
@Setter
@SequenceGenerator(allocationSize = 1, name = "users_id_seq")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "users_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "friendship",
            joinColumns = @JoinColumn(name = "requesting_user_id"),
            inverseJoinColumns = @JoinColumn(name = "receiving_user_id"))
    @OrderBy("id")
    private Set<User> friends;
}
