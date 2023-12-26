package com.example.blogsystemlab12.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Title should not be empty")
    @Column(columnDefinition = "varchar(35) not null")
    private String title;
    @NotEmpty(message = "Body should not be empty")
    @Column(columnDefinition = "varchar(250) not null")
    private String body;

    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "id")
    @JsonIgnore
    private User user;

}
