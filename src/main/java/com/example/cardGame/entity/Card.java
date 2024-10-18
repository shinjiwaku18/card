

package com.example.cardGame.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Cardテーブル用エンティティ
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

	@Id
	private Integer id;
	
	// カードの名前
	private String name;
	
	// カードの種類
	private Integer type;
}
