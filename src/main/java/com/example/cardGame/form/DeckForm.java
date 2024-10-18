
package com.example.cardGame.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeckForm {

	@NotNull(message="デッキが選択されていません")
	private Integer selected; // 選択したデッキを保持する
}