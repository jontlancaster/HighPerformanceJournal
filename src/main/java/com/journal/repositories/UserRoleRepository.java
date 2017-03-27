package com.journal.repositories;

import com.journal.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jonathon lancaster on 1/30/2017.
 */
@Repository
public interface UserRoleRepository extends JpaRepository <UserRole, Integer> {
    UserRole save(UserRole role);

    UserRole findByUserRoleId(int userRoleId);

    List<UserRole> findByUsername(String username);

    List<UserRole> findByRole(String role);
}
