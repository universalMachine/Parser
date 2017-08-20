
package com.parser.BacktrackParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.parser.lexer.Lexer;
import com.parser.lexer.ListLexer;
import com.parser.lexer.Token;

public class Parser {
	Lexer lexer;
	List<Integer> markers;
	List<Token> lookAhead;
	int pos = 0;
	protected final int FAILED = -1;
	
	public Parser(Lexer lexer)
	{
		this.lexer = lexer;
		lookAhead=new ArrayList<Token>();
		markers=new ArrayList<Integer>();
		sync(1);
	}
	
	
	public void match(int x) throws RecognitionException {
		if(LT(1)==x) cosume();
		else throw new RecognitionException("except "+ListLexer.TokenName[x]+";found "+LA(1));
		
	}
	
	public void cosume() {
		pos++;
		if(pos == lookAhead.size()-1&&!isSpeculating()) {
			pos = 0;
			lookAhead.clear();
		}
		
		sync(1);
		
	}
	
	public boolean alreadyParsedRule(Map<Integer,Integer> memo) throws PreviousParseFailedException {
		Integer status = memo.get(index());
		if(status==null) return false;
		if (status == FAILED) throw new PreviousParseFailedException();
		System.out.println("parsed before at index "+index()+" skip ahead to token index "+ status
												+": "+lookAhead.get(status).text);
		seek(status);
		return true;
	}
	
	protected void memorize(Map<Integer,Integer> memo,int startTokenIndex,boolean isParseFailed) {
		int stopTokenIndex = isParseFailed?FAILED:index();
		memo.put(startTokenIndex, stopTokenIndex);
	}
	
	public Token LA(int i) {
		sync(i);
		return lookAhead.get(pos+i-1);
	}
	
	public Integer LT(int i) {
		return LA(i).type;
	}
	
	private void sync(int i) {
		if(isOutBound(i)){
			fill(pos+i-1 - (lookAhead.size()-1));
		}
	}
	
	private boolean isOutBound(int i) {
		return pos+i-1>lookAhead.size()-1;
	}
	
	
	private void fill(int paddingSize) {
		for(int i=0;i<paddingSize;i++) {
			lookAhead.add(lexer.nextToken());
		}
	}
	
	protected void mark() {
		markers.add(pos);
	}
	
	protected void release() {
		int marker = markers.get(markers.size()-1);
		markers.remove(markers.size()-1);
		seek(marker);
	}
	
	private void seek(int marker) {
		pos = marker;
	}
	
	protected boolean isSpeculating() {
		return markers.size()>0;
	}
	
	protected int index() {
		return pos;
	}
}
