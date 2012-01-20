package com.kizzx2.cminus;

import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

import com.kizzx2.cminus.parser.CMinusLexer;
import com.kizzx2.cminus.parser.CMinusParser;
import com.kizzx2.cminus.parser.CMinusParser.program_return;

public class Program
{
    public static void main(String[] args) throws RecognitionException, IOException
    {
        CMinusLexer lexer = new CMinusLexer(new ANTLRFileStream("test.cm"));
        CMinusParser parser = new CMinusParser(new CommonTokenStream(lexer));
        program_return program = parser.program();
        CommonTree tree = (CommonTree)program.getTree();
        System.out.println(tree.toStringTree());
    }

}
