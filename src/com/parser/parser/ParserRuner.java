package com.parser.parser;

import com.parser.lexer.Lexer;
import com.parser.lexer.ListLexer;

public class ParserRuner {
	public static void main(String[] args) {
		ListLexer listLexer = new ListLexer("[a,[a=b],[c,[c=x]]]");
		ListParser listParser = new ListParser(listLexer,3);
		listParser.list();
	}
	
	
	
}
