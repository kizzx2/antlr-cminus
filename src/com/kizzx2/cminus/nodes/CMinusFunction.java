package com.kizzx2.cminus.nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class CMinusFunction implements IEvaluator
{
    String type;
    String id;
    
    // label -> type
    HashMap<String, String> params;
    
    ArrayList<CMinusDeclaration> decls;
    ArrayList<IEvaluator> statements;

    public CMinusFunction(
        String type,
        String id,
        HashMap<String, String> params,
        ArrayList<CMinusDeclaration> decls,
        ArrayList<IEvaluator> statements)
    {
        super();
        this.type = type;
        this.id = id;
        this.params = params;
        this.decls = decls;
        this.statements = statements;
    }
    
    public String getId()
    {
        return id;
    }


    @Override
    public CMinusValue eval(CMinusState state) throws Exception
    {
        // bind calling parameters
        Iterator<Entry<String, String>> it = params.entrySet().iterator();
        while(it.hasNext())
        {
            Entry<String, String> p = it.next();
            String id = p.getKey();
            String type = p.getValue();
            
            CMinusValue popped = state.stack.pop();
            if(!popped.type.equals(type))
                throw new Exception("type mismatch");
            
            state.pushVariable(id, popped);
        }
        
        for(CMinusDeclaration d : decls)
            d.enter(state);
        
        CMinusValue rv = null;
        for(IEvaluator e : statements)
        {
            CMinusValue result = e.eval(state);
            if(e instanceof CMinusReturn)
            {
                // pop calling parameters
                it = params.entrySet().iterator();
                while(it.hasNext())
                {
                    Entry<String, String> p = it.next();
                    String id = p.getKey();
                    state.popVariable(id);
                }
                
                rv = result;
                break;
            }
        }
        
        for(CMinusDeclaration d : decls)
            d.exit(state);
        
        if(rv == null && !type.equals("void"))
            throw new Exception("no return statement in function");
        if(!rv.type.equals(type))
            throw new Exception("return value type mismatch");
        
        return rv;
    }

}
