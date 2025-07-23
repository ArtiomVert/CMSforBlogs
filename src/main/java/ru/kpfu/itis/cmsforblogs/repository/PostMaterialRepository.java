package ru.kpfu.itis.cmsforblogs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.cmsforblogs.entity.PostMaterial;

@Repository
public interface PostMaterialRepository extends JpaRepository<PostMaterial, Long> {

}

