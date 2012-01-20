package com.kizzx2.cminus;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import com.kizzx2.cminus.parser.CMinusLexer;
import com.kizzx2.cminus.parser.CMinusParser;

public class Program
{
    public static void main(String[] args) throws RecognitionException
    {
        CMinusLexer lexer = new CMinusLexer(new ANTLRStringStream("int x;"));
        CMinusParser parser = new CMinusParser(new CommonTokenStream(lexer));
        parser.program();
    }

}
