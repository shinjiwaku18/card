
package com.example.cardGame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cardGame.component.CardInfomation;
import com.example.cardGame.entity.MonsterCard;
import com.example.cardGame.form.DeckForm;
import com.example.cardGame.form.NpcForm;
import com.example.cardGame.form.PlayerForm;
import com.example.cardGame.service.CardGameService;

@Controller
@RequestMapping("/cardgame")
public class CardGameController {

	@Autowired
	CardGameService service;
	
	@Autowired
	CardInfomation info;
	
	@ModelAttribute("deckForm")
	public DeckForm setUpDeckForm() {
		return new DeckForm();
	}
	
	@ModelAttribute("npcForm")
	public NpcForm setUpNpcForm() {
		return new NpcForm();
	}
	
	@ModelAttribute("playerForm")
	public PlayerForm setUpPlayerForm() {
		return new PlayerForm();
	}
	
	@GetMapping
	public String showTop() {
		return "top";
	}

	/**
	 * @param dForm
	 * @return "select"
	 * デッキ選択画面を表示する
	 */
	@GetMapping("/select")
	public String showSelect(DeckForm dForm, Model model) {
		// データベースからモンスターの情報を取得する
		service.selectAllMonsterCard();
		return "select";
	}

	/**
	 * @return "rule"
	 * 遊び方画面を表示する
	 */
	@GetMapping("/rule")
	public String showRule() {
		return "rule";
	}

	/**
	 * @param dForm
	 * @param pForm
	 * @param nForm
	 * @param bindingResult
	 * @param model
	 * @return "battle"
	 * バトル画面（モンスターカードとその攻撃力）を表示する
	 */
	@PostMapping("/play") // 
	public String showPlay(DeckForm dForm, PlayerForm pForm, NpcForm nForm, BindingResult bindingResult, Model model) {

		List<MonsterCard> pDeck = info.getPDeck(); // プレイヤーの使用するデッキ
		List<MonsterCard> nDeck = info.getNDeck(); // npcの使用するデッキ
		MonsterCard pMonster = new MonsterCard();
		MonsterCard nMonster = new MonsterCard();
		
		// デッキ選択画面で、デッキを選択せずに「決定」ボタンを押したとき、select.htmlを返す
		if (dForm.getSelected() == null) {
			model.addAttribute("err", true);
			return "select";
		}
		
		// 1回目の表示の時（＝デッキが組まれていないとき）
		if(pDeck == null || pDeck.size() == 0) {
			// プレイヤーのデッキを選択をもとに一つ用意する
			pDeck = service.selectDeckById(service.createDeckList(),dForm.getSelected()-1);
			info.setPDeck(pDeck);

			// npcのデッキをランダムに一つ用意する
			nDeck = service.selectOneRandomDeck(service.createDeckList());
			info.setNDeck(nDeck);
		}
		
		// バトルするモンスターを選出
		pMonster = service.selectOneRandomCard(pDeck); // プレイヤーのモンスター
		nMonster = service.selectOneRandomCard(nDeck); // npcのモンスター

		// バトルするモンスターの画像を獲得する
		String pUrl = getUrl(pMonster.getId());
		String nUrl = getUrl(nMonster.getId());

		// 必要な情報をmodelに詰める
		pForm.setPMonster(pMonster);
		pForm.setPUrl(pUrl);
		model.addAttribute(pForm);

		nForm.setNUrl(nUrl);
		nForm.setNMonster(nMonster);
		model.addAttribute(nForm);
		
		model.addAttribute(dForm);

		return "battle";
	}

	@PostMapping("/result")
	public String showResult(DeckForm dForm, PlayerForm pForm, NpcForm nForm, BindingResult bindingResult, Model model){
		
		// 初回のみ
		if(info.getPDeck().size() == 4) {
			// PlayerForm の int won に０をセットする
			pForm.setWon(0);
		}
		
		// プレイヤーの勝利回数を保管
		int won = pForm.getWon();

		// 場に出ているモンスターカードの勝敗判定を行う
	    boolean isStronger = service.checkMonsterVictoryOfDefeat(pForm.getPMonster(), nForm.getNMonster(), won);
	    
	    if(isStronger) {  // プレイヤーが勝利したとき
	    	// プレイヤーに「win」,npcに「lose」のメッセージを持たせる
	    	model.addAttribute("pMsg", "win!!");
	    	model.addAttribute("nMsg", "lose...");
	    	
	    	// プレイヤーの勝利回数を１増やす
	    	pForm.setWon(won +1);
	    	
	    }else {  // プレイヤーが敗北したとき
	    	// プレイヤーに「lose」,npcに「win」のメッセージを持たせる
	    	model.addAttribute("pMsg", "lose...");
	    	model.addAttribute("nMsg", "win!!");
	    }
	    
	    boolean next = true; // 次回もバトルフェーズに移行するかの判定

	    // 最終回の判定
	    if(info.getPDeck().size() == 0) {
	    	// ゲームの勝敗を判定する
	    	boolean victory = service.checkPlayerVictoryOfDefeat(won);
	    	pForm.setResult(victory);
	    	
	    	// 「バトルを終了する」ボタンを表示する
	    	next = false;
	    }
	    
	    model.addAttribute(pForm);
		model.addAttribute(nForm);
		model.addAttribute(dForm);
		model.addAttribute("next", next);

		return "result";
	}
	
	@PostMapping("/end")
	public String showEnd(DeckForm dForm, PlayerForm pForm, Model model) {
		// 選択したデッキを詰める
		model.addAttribute(dForm);
		
		// ゲームの勝敗を判定する
		boolean result = service.checkPlayerVictoryOfDefeat(pForm.getWon());
		
		if(result) {  // プレイヤーが３回以上勝利したとき
			model.addAttribute("msg", "あなたの勝ち！！！");
		}else {  // プレイヤーが３回以上敗北したとき
			model.addAttribute("msg", "あなたの負け．．．");
		}
		return "end";
	}

	@PostMapping("/deck")
	public String showDeck(DeckForm dForm, BindingResult bindingresult, Model model) {
		if (dForm.getSelected() == null) {
			model.addAttribute("err", true);
			return "select";
		}
		model.addAttribute(dForm);
		return "show";
	}
	
	public String getUrl(Integer id) {
		String url = switch(id) {
		case 1 -> "/images/usa1.jpg";
		case 2 -> "/images/usa2.jpg";
		case 3 -> "/images/usa3.jpg";
		case 4 -> "/images/usa4.jpg";
		case 5 -> "/images/usa5.jpg";
		case 6 -> "/images/hamu1.jpg";
		case 7 -> "/images/hamu2.jpg";
		case 8 -> "/images/hamu3.jpg";
		case 9 -> "/images/hamu4.jpg";
		case 10 -> "/images/hamu5.jpg";
		case 11 -> "/images/wara1.jpg";
		case 12 -> "/images/wara2.jpg";
		case 13 -> "/images/wara3.jpg";
		case 14 -> "/images/wara4.jpg";
		case 15 -> "/images/wara5.jpg";
		default -> "エラー";
		};
		
		return url;
	}
}
