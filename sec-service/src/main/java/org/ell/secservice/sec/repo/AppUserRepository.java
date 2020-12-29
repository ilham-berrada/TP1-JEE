package org.ell.secservice.sec.repo;

import org.ell.secservice.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository  extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
