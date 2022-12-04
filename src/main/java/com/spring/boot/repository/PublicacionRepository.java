package com.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.entity.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {

}
