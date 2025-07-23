package ru.kpfu.itis.cmsforblogs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.kpfu.itis.cmsforblogs.dictionary.UserRole;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hashPassword;

    @Column(nullable = false, unique = true)
    private String username;

    @Enumerated(STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    public void addPost(Post post){
        posts.add(post);
        post.setUser(this);
    }

}

