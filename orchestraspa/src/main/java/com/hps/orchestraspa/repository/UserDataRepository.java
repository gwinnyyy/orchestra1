package com.hps.orchestraspa.repository;
import com.hps.orchestraspa.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserDataRepository extends JpaRepository<UserData, Integer>{
    Optional<UserData> findByUserEmail(String userEmail);
}
