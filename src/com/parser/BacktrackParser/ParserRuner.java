package com.parser.BacktrackParser;

import com.parser.lexer.Lexer;
import com.parser.lexer.ListLexer;

public class ParserRuner {
	public static void main(String[] args) {
		ListLexer listLexer = new ListLexer("[a,[a=b],[c,[c=x]]]=[c,d]");
		ListParser listParser = new ListParser(listLexer);
		try {
			listParser.stat();
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
