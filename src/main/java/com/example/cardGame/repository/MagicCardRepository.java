
package com.example.cardGame.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.cardGame.entity.Card;
import com.example.cardGame.entity.MagicCard;

public interface MagicCardRepository extends CrudRepository<MagicCard, Integer>{
	
	@Query("select * from public.magiccard")
	Iterable<Card> selectAllMagicCard();
	
}
