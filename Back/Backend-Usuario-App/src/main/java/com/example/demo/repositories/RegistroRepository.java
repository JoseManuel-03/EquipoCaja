package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.RegistroPractica;

public interface RegistroRepository extends JpaRepository<RegistroPractica, Long>{
	
	public Optional findById(Long id);

}
