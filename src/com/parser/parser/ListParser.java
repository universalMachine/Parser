package com.parser.parser;

import com.parser.lexer.Lexer;
import com.parser.lexer.ListLexer;

public class ListParser extends Parser {
	public ListParser(Lexer lexer,int k) {
		super(lexer,k);
		
	}

	public void list() {
		match(ListLexer.LBRACK);elements();match(ListLexer.RBRACK);	
		System.out.println("parser success");
	}
	
	public void elements() {
		element();
		while(LT(1) == ListLexer.COMMA) {
			match(ListLexer.COMMA);element();
		}
	}
	
	public void element() {
		if(LT(1)==ListLexer.NAME&&LT(2) == ListLexer.EQUAL) {
			match(ListLexer.NAME);match(ListLexer.EQUAL);match(ListLexer.NAME);
		}
		else if(LT(1)==ListLexer.NAME) match(ListLexer.NAME);	
		else if(LT(1)==ListLexer.LBRACK) list();
		else throw new Error("excepting name or list;found "+lookAhead);
	}
}
