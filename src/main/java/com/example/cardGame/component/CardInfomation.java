

package com.example.cardGame.component;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.cardGame.entity.MonsterCard;
import com.example.cardGame.form.NpcForm;
import com.example.cardGame.form.PlayerForm;

import lombok.Data;

@Component
@Data
public class CardInfomation {

	private List<MonsterCard> pDeck; // Playerのデッキ
	private PlayerForm pForm;
	private List<MonsterCard> nDeck; // Npcのデッキ
	private NpcForm nForm;
}

