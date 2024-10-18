//package com.example.cardGame;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class CardApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(CardApplication.class, args);
//	}
//
//}


package com.example.cardGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
//		.getBean(CardGameApplication.class).execute();
	}
	
//	@Autowired
//	MonsterCardRepository monsterCardRepository;
//	
//	private void execute() {
//		setUpDeck();
//	}
//	
//	private void setUpDeck() {
//		
//		List<List<MonsterCard>> deckList = new ArrayList<>();
//		for (int i = 1; i < 4; i++) {
//			deckList.add(monsterCardRepository.findByDeckCode(i));
//		}
//		for (List<MonsterCard> deck : deckList) {
//			for (MonsterCard card : deck) {
//				System.out.println(card.getName());
//			}
//		}
//	}
}

