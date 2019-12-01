package com.app.requestanalyzer.repo;

import com.app.requestanalyzer.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Client getClientByClientId(String id);
}
