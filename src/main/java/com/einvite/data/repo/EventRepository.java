package com.einvite.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einvite.data.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
