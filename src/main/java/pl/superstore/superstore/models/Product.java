package pl.superstore.superstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@AllArgsConstructor
@Setter
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    private String name;

    private String description;



}
