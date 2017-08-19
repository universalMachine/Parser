package com.parser.parser;
import com.parser.lexer.*;
public class Parser {
	Lexer lexer;
	Token[] lookAhead;


	private final int k;
	private int pos = 0;
	
	public Parser(Lexer lexer,int k) {
		this.lexer = lexer;
		this.k = k;
		lookAhead = new Token[k];
		for(int i=0;i<k;i++) {
			consume();
		}
		pos = 0;
	}
	
	Token LA(int i) {
		return lookAhead[(pos+i-1)%k];
	}
	
	int LT(int i) {
		return LA(i).type;
	}
	
	void match(int x) {
		if(LT(1) == x) consume();
		else throw new Error("exception "+lexer.getTokenName(x)+
														"; found"+LA(1));
	}
	
	void consume() {
		lookAhead[pos] = lexer.nextToken();
		pos= (pos+1)%k;
	}
}
