

package com.example.cardGame.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// monstercardテーブル用のエンティティ
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "monstercard")
public class MonsterCard {

	@Id
	private Integer id;
	
	// モンスターの名前
	private String name;
	
	// モンスターの攻撃力
	private Integer atk;
	
//	// モンスターの防御力
//	private Integer def;
//	
//	// モンスターのレアリティ
//	private Integer rarity;
//	
	// デッキコード
	@Column(value = "deckcode")
	private Integer deckCode;
}
