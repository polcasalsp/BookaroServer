package com.bookaro.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bookaro.server.model.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

}
