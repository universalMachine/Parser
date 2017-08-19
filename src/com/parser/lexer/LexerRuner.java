package com.parser.lexer;

public class LexerRuner {

	public static void main(String[] args) {

		ListLexer lexer = new ListLexer("[a, bcd]");
		
		Token tok = lexer.nextToken();
		
		while(tok.type!=Lexer.EOF_TYPE) {
			System.out.println(tok);
			tok = lexer.nextToken();
		}
		
		System.out.println(tok); //EOF

	}

}
