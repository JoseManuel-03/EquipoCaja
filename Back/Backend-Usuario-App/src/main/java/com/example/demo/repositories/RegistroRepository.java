package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Alumno;
import com.example.demo.model.RegistroPractica;

public interface RegistroRepository extends JpaRepository<RegistroPractica, Long> {

	public List<RegistroPractica> findByAlumno(Alumno alumno);

}
