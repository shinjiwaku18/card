
package com.example.cardGame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cardGame.entity.MonsterCard;
import com.example.cardGame.repository.MonsterCardRepository;

@Service
@Transactional
public class CardGameServiceImpl implements CardGameService {
	/** Repository：注入 */
	@Autowired
	MonsterCardRepository monsterCardRepository;
//	@Autowired
//	MagicCardRepository magicCardRepository;
	
	@Override
	public Iterable<MonsterCard> selectAllMonsterCard(){
		return monsterCardRepository.findAll();
	}
	
//	@Override
//	public Iterable<MagicCard> selectAllMagicCard(){
//		return magicCardRepository.findAll();
//	}

	@Override
	public List<MonsterCard> selectAllMonsterCard(Integer deckCode) {
		return monsterCardRepository.findByDeckCode(deckCode);
	}

//	@Override
//	public Optional<MagicCard> selectOneMagicCard(Integer id) {
//		return magicCardRepository.findById(id);
//	}
	
	@Override
	public List<List<MonsterCard>> createDeckList() {
		
		List<List<MonsterCard>> deckList = new ArrayList<>();
		// デッキコード別に取得したモンスターカードリストを３つのデッキリストに格納
		for (int i = 1; i < 4; i++) {
			deckList.add(selectAllMonsterCard(i));
		}
		
		return deckList;
	}
	
	@Override
	public List<MonsterCard> selectOneRandomDeck(List<List<MonsterCard>> deckList) {
		
		Random rand = new Random();
		return deckList.get(rand.nextInt(deckList.size()));
	}

	@Override
	public List<MonsterCard> selectDeckById(List<List<MonsterCard>> deckList, Integer no) {
		
		return deckList.get(no);
	}

//	@Override
//	public void addHp(Player player) {
//		player.hp += 1000;
//	}

//	@Override
//	public void subtractHp(Player player) {
//		
//	}

	@Override
	public MonsterCard selectOneRandomCard(List<MonsterCard> deck) {
		Random rand = new Random();
		
		MonsterCard monsterCard = deck.get(rand.nextInt(deck.size()));
		deleteCardById(deck, monsterCard);
		
		return monsterCard;
	}

	@Override
	public List<MonsterCard> deleteCardById(List<MonsterCard> deck, MonsterCard monsterCard) {
		deck.remove(deck.indexOf(monsterCard));
		return deck;
	}

//	@Override
//	public List<Card> addHandOfCards(Integer id) {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}

//	@Override
//	public List<Card> subtractHandOfCards(Integer id) {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}

//	@Override
//	public MonsterCard addMonsterField(MonsterCard monsterCard) {
//		return monsterCard;
//	}
//
//	@Override
//	public List<MonsterCard> subtractMonsterField(Integer id) {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}

//	@Override
//	public Integer useMagicCard(Integer id) {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}

//	@Override
//	public void addCardToGraveyard(Integer id) {
//		// TODO 自動生成されたメソッド・スタブ
//		
//	}

//	@Override
//	public boolean checkMode() {
//		// TODO 自動生成されたメソッド・スタブ
//		return false;
//	}

	@Override
	public boolean checkMonsterVictoryOfDefeat(MonsterCard monsterCard1, MonsterCard monsterCard2, int numberOfWins) {
		// trueなら勝利、falseなら敗北
		boolean isStronger = false;
		
		if (monsterCard1.getAtk() > monsterCard2.getAtk()) {
			addNumberOfWins(numberOfWins);
			isStronger = true;
		} else if (monsterCard1.getAtk() == monsterCard2.getAtk()) {
			// 攻撃力の数値が同じ時は運ゲーにする
			Random rand = new Random();
			int strong = rand.nextInt(2);
			switch (strong) {
			case 0 -> isStronger = false;
			case 1 -> isStronger = true;
			}
			if (isStronger) {
				addNumberOfWins(numberOfWins);
			}
		}
		
		return isStronger;
	}
	
	@Override
	public Integer addNumberOfWins(int numberOfWins) {
		return numberOfWins++;
	}
	
	@Override
	public boolean checkPlayerVictoryOfDefeat(int numberOfWins) {
		boolean isWinner = false;
		if (numberOfWins > 2) {
			isWinner = true;
		}
		return isWinner;
	}
	
}

