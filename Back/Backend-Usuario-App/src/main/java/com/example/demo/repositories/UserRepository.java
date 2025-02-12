package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Usuario;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findOneByNombreUsuario(String nombreUsuario);
	
	
}
