package com.cathotel.cathotel.repository;

import com.cathotel.cathotel.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository  extends JpaRepository<Registration, Long> {
}
