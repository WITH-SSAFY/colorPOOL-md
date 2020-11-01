package com.cerberos.colorpool.repository.theme;

import com.cerberos.colorpool.entity.theme.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface ThemeJpaRepository extends JpaRepository<Theme, Integer> {

}
