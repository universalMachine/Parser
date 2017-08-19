package com.parser.lexer;

public class ListLexer extends Lexer {
	public static final int NAME =2;
	public static final int COMMA =3;
	public static final int LBRACK =4;
	public static final int RBRACK =5;
	public static final int EQUAL = 6;
	
	
	public static String[] TokenName = {"n/a","<EOF>","NAME","COMMA","LBRACK","RBRACK","EQUAL"};
	
	

	public ListLexer(String input) {
		super(input);
		
	}
	
	
	

	@Override
	public Token nextToken() {
		while(c!=EOF) {
			switch(c) {
			case ' ':case '\t':case '\n':case '\r': WS();continue;
			case ',':consume();return new Token(COMMA,",");
			case '[':consume();return new Token(LBRACK,"[");
			case ']':consume();return new Token(RBRACK,"]");
			case '=':consume();return new Token(EQUAL,"=");
			default:
				if(isLetter()) return Name();
				throw new Error("invalid character: "+c);

			
			}
			
			
		}
		
		return new Token(EOF_TYPE,"<EOF>");
	}

	@Override
	public String getTokenName(int tokenType) {
		// TODO Auto-generated method stub
		return TokenName[tokenType];
	}
	
	
	void WS() {
		while(c== ' '||c=='\t'||c=='\n'|c=='\r') consume();
	}
	
	boolean isLetter(){
		return c>='a'&&c<='z'||c>='A'&&c<='Z';
	}
	
	Token Name() {
		
		StringBuilder sb = new StringBuilder();
		while(isLetter()) {
			sb.append(c);			
			consume();
			
		}
		
		return new Token(NAME,sb.toString());
	}
	
	
	

}
