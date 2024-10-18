

package com.example.cardGame.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardForm {

	private Integer id; // カードのid
	private String url; // カードの画像
	private Integer atk; // 攻撃力
}