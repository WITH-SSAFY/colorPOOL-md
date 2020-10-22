package com.cerberos.colorpool.repository.theme;

import com.cerberos.colorpool.entity.theme.RecommendTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendJpaRepository extends JpaRepository<RecommendTheme, Integer> {

}
