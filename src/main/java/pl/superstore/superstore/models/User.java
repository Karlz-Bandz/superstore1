package pl.superstore.superstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String mail;

    private String phoneNumber;

    //Here are a space for a payment data
}
