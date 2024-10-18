
package com.example.cardGame.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.cardGame.entity.Card;

public interface CardRepository extends CrudRepository<Card, Integer> {

	@Query("select * from public.card")
	Iterable<Card> selectAll();
}