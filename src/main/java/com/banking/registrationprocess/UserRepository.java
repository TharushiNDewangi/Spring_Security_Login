package com.banking.registrationprocess;

import org.springframework.data.jpa.repository.JpaRepository;

//type ek user id ek long nisa long
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
