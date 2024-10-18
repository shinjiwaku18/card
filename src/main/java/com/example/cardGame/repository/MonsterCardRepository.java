
package com.example.cardGame.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cardGame.entity.MonsterCard;

public interface MonsterCardRepository extends CrudRepository<MonsterCard, Integer>{
	
//	@Query("select * from public.monstercard")
//	Iterable<Card> selectAllMonsterCard();
	
	@Query("select * from public.monstercard where deckcode = :deckcode")
	List<MonsterCard> findByDeckCode(@Param("deckcode")Integer deckCode);
}

