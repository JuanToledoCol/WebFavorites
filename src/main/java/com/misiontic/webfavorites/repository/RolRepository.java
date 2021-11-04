package com.misiontic.webfavorites.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misiontic.webfavorites.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

}
