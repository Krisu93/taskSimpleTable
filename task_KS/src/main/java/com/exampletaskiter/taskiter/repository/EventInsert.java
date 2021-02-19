package com.exampletaskiter.taskiter.repository;

import com.exampletaskiter.taskiter.entity.EventDetails;
import org.springframework.data.repository.CrudRepository;

public interface EventInsert extends CrudRepository<EventDetails, Long> {
}
