/**
 * 	JapaneseKeyCodes - 	
 *	
 *		Primary Use:
 *		- maps english phonetics to unicode value of japanese characters
 *
 *		Functions:	
 *		- getKey @param String value
 *			>O(n) operation where n = keys
 *			>linearly searches the toJapanese hashmap for the key with value @param
 *			>Used for handling backspacing in JapaneseKeyboard.printText
 *
 *
 **/
import java.util.Map;
import java.util.HashMap;

public class JapaneseKeyCodes
{
	public static Map<String, String> toJapanese = new HashMap<>();
	
	public static String getKey(String value) {
		for (String key : toJapanese.keySet()) {
			if (value.equals(toJapanese.get(key))) {
				return key;
			}
		}
		return null;
	}

	public static void populateMap()
	{
	//Hiragana
		//vowels
		toJapanese.put("a" ,"\u3042");
		toJapanese.put("i" ,"\u3044");
		toJapanese.put("u" ,"\u3046");
		toJapanese.put("e" ,"\u3048");
		toJapanese.put("o" ,"\u304A");
		//"k"
		toJapanese.put("ka" ,"\u304B");
		toJapanese.put("ki" ,"\u304D");
		toJapanese.put("ku" ,"\u304F");
		toJapanese.put("ke" ,"\u3051");
		toJapanese.put("ko" ,"\u3053");
		//"s"
		toJapanese.put("sa" ,"\u3055");
		toJapanese.put("shi","\u3057");
		toJapanese.put("su" ,"\u3059");
		toJapanese.put("se" ,"\u305B");
		toJapanese.put("so" ,"\u305D");
		//"t"
		toJapanese.put("ta" ,"\u305F");
		toJapanese.put("chi","\u3061");
		toJapanese.put("tsu","\u3064");
		toJapanese.put("te" ,"\u3066");
		toJapanese.put("to" ,"\u3068");

		//"n"
		toJapanese.put("na", "\u306A");
		toJapanese.put("ni", "\u306B");
		toJapanese.put("nu", "\u306C");
		toJapanese.put("ne", "\u306D");
		toJapanese.put("no", "\u306E");

		//"h"
		toJapanese.put("ha", "\u306F");
		toJapanese.put("hi", "\u3072");
		toJapanese.put("fu", "\u3075");
		toJapanese.put("he", "\u3078");
		toJapanese.put("ho", "\u307B");

		//"m"
		toJapanese.put("ma", "\u307E");
		toJapanese.put("mi", "\u307F");
		toJapanese.put("mu", "\u3080");
		toJapanese.put("me", "\u3081");
		toJapanese.put("mo", "\u3082");

		//"y"
		toJapanese.put("ya", "\u3084");
		toJapanese.put("yu", "\u3086");
		toJapanese.put("yo", "\u3088");

		//"r"
		toJapanese.put("ra", "\u3089");
		toJapanese.put("ri", "\u308A");
		toJapanese.put("ru", "\u308B");
		toJapanese.put("re", "\u308C");
		toJapanese.put("ro", "\u308D"); 

		//"w"
		toJapanese.put("wa", "\u308F");
		toJapanese.put("wo", "\u3092");

		//"n"
		toJapanese.put("n" , "\u3093");

		//"g"
		toJapanese.put("ga" , "\u304C");
		toJapanese.put("gi" , "\u304E");
		toJapanese.put("gu" , "\u3050");
		toJapanese.put("ge" , "\u3052");
		toJapanese.put("go" , "\u3054");

		//"z"
		toJapanese.put("za" , "\u3056");
		toJapanese.put("ji" , "\u3058");
		toJapanese.put("zu" , "\u305A");
		toJapanese.put("ze" , "\u305C");
		toJapanese.put("zo" , "\u305E");
		//"d"
		toJapanese.put("da" , "\u3060");
		//Uncommonly used characters -- to be implemented later
//		toJapanese.put("ji" , "\u3062"); 
//		toJapanese.put("zu" , "\u3065");
		toJapanese.put("de" , "\u3067");
		toJapanese.put("do" , "\u3069");

		//"b"
		toJapanese.put("ba" , "\u3070");
		toJapanese.put("bi" , "\u3073");
		toJapanese.put("bu" , "\u3076");
		toJapanese.put("be" , "\u3079");
		toJapanese.put("bo" , "\u307C");

		//"p"
		toJapanese.put("pa" , "\u3071");
		toJapanese.put("pa" , "\u3074");
		toJapanese.put("pa" , "\u3077");
		toJapanese.put("pa" , "\u307A");
		toJapanese.put("pa" , "\u307D");
	//TODO:katakana

	//TODO:kanji + logic 

	}
}