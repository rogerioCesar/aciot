package com.rctecnologia.aciot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rctecnologia.aciot.model.User;


/**
 * Interface padrão de um JPA Repository.
 * Executa funções de persistência de dados dos usuários do ambiente IoT
 * @author rogerio
 *
 */
public interface UserRepository extends JpaRepository <User, Long> {
}
