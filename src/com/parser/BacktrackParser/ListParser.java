package com.parser.BacktrackParser;

import java.util.HashMap;
import java.util.Map;

import com.parser.lexer.Lexer;
import com.parser.lexer.ListLexer;

public class ListParser extends Parser {
	
	Map<Integer,Integer> listMemo = new HashMap<Integer,Integer>();

	public ListParser(Lexer lexer) {
		super(lexer);
		
	}
	/*stat: list EOF|assign EOF*/
	public void stat() throws RecognitionException {
		if(speculate_stat_alt1()) {
			list();match(ListLexer.EOF_TYPE);
			System.out.println("stat: list");
		}
		else if(speculate_stat_alt2()) {
			list();match(ListLexer.EQUAL);list();match(ListLexer.EOF_TYPE);
			System.out.println("stat: list = list");
		}
		else throw new Error("Excepting stat;found "+LA(1));
	}



public boolean speculate_stat_alt1() {
	System.out.println("attempt stat alternative 1");
	boolean success = true;
	mark();
	try {
		list();match(ListLexer.EOF_TYPE);
	} catch (RecognitionException e) {
		success = false;
	}
	release();
	return success;
}

public boolean speculate_stat_alt2() {
	System.out.println("attempt stat alternative 2");
	boolean success = true;
	mark();
	try {
		list();match(ListLexer.EQUAL);list();match(ListLexer.EOF_TYPE);
	} catch (RecognitionException e) {
		success = false;
	}
	release();
	return success;
}

public void list() throws RecognitionException{
	int startTokenIndex = index();
	boolean failed = false;
	try {
		if(isSpeculating()&&alreadyParsedRule(listMemo)) return;
	} catch (PreviousParseFailedException e) {
		return;
	}
	try {
		_list();
	} catch (RecognitionException re) {
		failed = true;
		
		throw re;
	}finally {
		if(isSpeculating()) memorize(listMemo,startTokenIndex,failed);
	}
	
}
public void _list() throws RecognitionException {
	
	if(isSpeculating()) System.out.println("parse list rule at token index:"+index());
	match(ListLexer.LBRACK);elements();match(ListLexer.RBRACK);	
	if(!isSpeculating())
	System.out.println("parser success");
}

public void elements() throws RecognitionException {
	element();
	while(LT(1) == ListLexer.COMMA) {
		match(ListLexer.COMMA);element();
	}
}

public void element() throws RecognitionException {
	if(LT(1)==ListLexer.NAME&&LT(2) == ListLexer.EQUAL) {
		match(ListLexer.NAME);match(ListLexer.EQUAL);match(ListLexer.NAME);
	}
	else if(LT(1)==ListLexer.NAME) match(ListLexer.NAME);	
	else if(LT(1)==ListLexer.LBRACK) list();
	else throw new Error("excepting name or list;found "+lookAhead);
}
}