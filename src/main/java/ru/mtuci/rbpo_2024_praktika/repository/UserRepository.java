package ru.mtuci.rbpo_2024_praktika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mtuci.rbpo_2024_praktika.model.ApplicationUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, UUID> {
    Optional<ApplicationUser> findByEmail(String email);
}
