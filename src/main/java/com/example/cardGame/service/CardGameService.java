

package com.example.cardGame.service;

import java.util.List;

import com.example.cardGame.entity.MonsterCard;

/** CardGameサービス処理：Service */
public interface CardGameService {
	/** モンスターカード情報を全件取得します */
	Iterable<MonsterCard> selectAllMonsterCard();
	/** 魔法カード情報を全件取得します */
//	Iterable<MagicCard> selectAllMagicCard();
	/** 指定したデッキコードを持つモンスターカードを取得 */
	List<MonsterCard> selectAllMonsterCard(Integer deckCode);
	/** 指定したIDを持つ魔法カードを取得 */
//	Optional<MagicCard> selectOneMagicCard(Integer id);
	/** デッキのリストを３つ作成 */
	List<List<MonsterCard>> createDeckList();
	/** デッキをランダムで1件取得 */
	List<MonsterCard> selectOneRandomDeck(List<List<MonsterCard>> deckList);
	/** 選択したデッキを１件取得 */
	List<MonsterCard> selectDeckById(List<List<MonsterCard>> deckList, Integer no);
	/** HPを増やす */
//	void addHp(Player player);
	/** HPを減らす */
//	void subtractHp(Player player);
	/** モンスターの攻撃モードと防御モードの判定 */
//	boolean checkMode();
	/** モンスターの勝敗判定 */
	boolean checkMonsterVictoryOfDefeat(MonsterCard monsterCard1, MonsterCard monsterCard2, int numberOfWins);
	/** モンスターの勝利回数管理 */
	Integer addNumberOfWins(int numberOfWins);
	/** 山札からカード情報をランダムで１件取得（戻り値：取得したカード） */
	MonsterCard selectOneRandomCard(List<MonsterCard> deck);
	/** 山札からカード情報を１件削除（戻り値：山札リスト）*/
	List<MonsterCard> deleteCardById(List<MonsterCard> deck, MonsterCard monsterCard);
	/** 手札にカード情報を登録（戻り値：手札リスト） */
//	List<Card> addHandOfCards(Integer id);
	/** 手札からカード情報を削除（戻り値：手札リスト） */
//	List<Card> subtractHandOfCards(Integer id);
	/** 山札から引いたモンスターカードをモンスターフィールドに登録する（戻り値：モンスターフィールドリスト） */
//	MonsterCard addMonsterField(MonsterCard monsterCard);
	/** モンスターフィールドからカード情報を削除（戻り値：モンスターフィールドリスト） */
//	List<MonsterCard> subtractMonsterField(Integer id);
	/** 手札から選んだ魔法カードを使用する */
//	Integer useMagicCard(Integer id);
	/** 墓地フィールドにカード情報を１件登録 */
//	void addCardToGraveyard(Integer id);
	/** 山札の枚数が０になった時にプレイヤーの勝敗判定 */
	boolean checkPlayerVictoryOfDefeat(int numberOfWins);
	
	
}

