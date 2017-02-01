package com.journal.repositories;

import com.journal.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jonathon lancaster on 1/30/2017.
 */
@Repository
public interface UserTypeRepository extends JpaRepository <UserType, Integer> {
    UserType findByUserTypeId(int userTypeId);
}
