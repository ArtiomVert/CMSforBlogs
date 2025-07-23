package ru.kpfu.itis.cmsforblogs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.cmsforblogs.entity.Post;
import ru.kpfu.itis.cmsforblogs.entity.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);

    @Query("SELECT p FROM Post p ORDER BY p.id DESC LIMIT 50")
    List<Post> findNew();
}

