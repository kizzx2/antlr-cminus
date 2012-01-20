package com.kizzx2.cminus;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import com.kizzx2.cminus.nodes.CMinusProgram;
import com.kizzx2.cminus.nodes.CMinusState;
import com.kizzx2.cminus.nodes.CMinusTree;
import com.kizzx2.cminus.nodes.CMinusValue;
import com.kizzx2.cminus.parser.CMinusLexer;
import com.kizzx2.cminus.parser.CMinusParser;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        CMinusLexer lexer = new CMinusLexer(new ANTLRFileStream("test.cm"));
        CMinusParser parser = new CMinusParser(new CommonTokenStream(lexer));
        CMinusParser.program_return program = parser.program();
        CommonTree tree = (CommonTree)program.getTree();
        
        System.out.println(tree.toStringTree());
        CMinusTree walker = new CMinusTree(new CommonTreeNodeStream(tree));
        
        
        CMinusTree.program_return ast = walker.program();
        CMinusProgram realProgram = ast.rv;
        System.out.println("parsing ok!");
        
        CMinusValue v = realProgram.eval(new CMinusState());
        System.out.println("program returned: <" +
            v.type + "> " + v.value);
    }

}
