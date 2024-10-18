

package com.example.cardGame.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// magiccardテーブル用エンティティ
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagicCard {

	@Id
	private Integer id;
	
	// カードの名前
	private String name;
	
	// 攻撃力に影響を与える際の数値
	private Integer atk;
	
	// 防御力に影響を与える際の数値
	private Integer def;
	
	// プレイヤーのHPに影響を与える際の数値
	private Integer operateHP;
	
	// カードの種類
	private Integer type;
}
