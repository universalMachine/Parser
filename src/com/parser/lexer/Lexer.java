package com.parser.lexer;

public abstract class Lexer {
	public static final char EOF = (char)-1;
	public static final int EOF_TYPE = 1;
	String input;
	Token lookahead;
	int pos = 0;
	char c;
	
	public Lexer(String input) {
		this.input = input;
		c = input.charAt(pos);
	}
	
	public void consume() {
		pos++;
		if(pos>=input.length()) c = EOF;
		else c = input.charAt(pos);
			}
	
  void match(char x) {
		if(c == x) consume();
		else throw new Error("expecting"+ x +"; found"+c);
	}
	
	public abstract Token nextToken();
	public abstract String getTokenName(int tokenType);
}
