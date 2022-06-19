package project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String username;
    @JsonIgnore
    private String password;
    @OneToMany(mappedBy = "userEntity")
    @JsonIgnore
    private List<EntryEntity> entries;

    public UserEntity(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public UserEntity() {}

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", entries=" + entries +
                '}';
    }
}