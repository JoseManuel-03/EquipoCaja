package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FechaPractica;

public interface FechaRepository extends JpaRepository<FechaPractica, Long> {
	
	List<FechaPractica> findByAnioCursoAndEvaluacion(Integer anioCurso, String evaluacion);

}
