
package com.example.cardGame.form;

import com.example.cardGame.entity.MonsterCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NpcForm {

	private MonsterCard nMonster; //フィールドに出ているモンスター
	private String nUrl; // モンスターの画像
}

