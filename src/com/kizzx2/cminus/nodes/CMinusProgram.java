package com.kizzx2.cminus.nodes;

import java.util.ArrayList;

public class CMinusProgram implements IEvaluator
{
    ArrayList<CMinusDeclaration> decls;
    ArrayList<CMinusFunction> funcs;
    
    
    public CMinusProgram(ArrayList<CMinusDeclaration> decls,
        ArrayList<CMinusFunction> funcs)
    {
        super();
        this.decls = decls;
        this.funcs = funcs;
    }


    @Override
    public CMinusValue eval(CMinusState state) throws Exception
    {
        for(CMinusDeclaration d : decls)
            d.enter(state);
        
        CMinusValue rv = null;
        for(CMinusFunction f : funcs)
        {
            state.pushVariable(f.getId(), new CMinusValue("function", f));
            if(f.getId().equals("main"))
            {
                rv = f.eval(state);
                break;
            }
        }
        
        for(CMinusDeclaration d : decls)
            d.exit(state);
        
        if(rv == null)
            throw new Exception("no main function declared");
        
        return rv;
    }

}
