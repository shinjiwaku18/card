
package com.example.cardGame.form;

import com.example.cardGame.entity.MonsterCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerForm {

	private MonsterCard pMonster; // フィールドに出ているモンスター
	private String pUrl; // モンスターの画像
	private Integer won; // 勝利回数
	private Boolean result; // バトルの勝敗
	
}

