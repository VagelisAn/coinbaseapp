package com.server.coinbase.repository;

import com.server.coinbase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByFirstnameAndLastnameOrEmail(String firstname, String lastname, String email);

    Optional<User> findByKeycloakId(String keycloakId);

}
