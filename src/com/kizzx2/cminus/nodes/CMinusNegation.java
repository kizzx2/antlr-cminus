package com.kizzx2.cminus.nodes;

public class CMinusNegation implements IEvaluator
{
    IEvaluator target;
    
    public CMinusNegation(IEvaluator target)
    {
        super();
        this.target = target;
    }

    @Override
    public CMinusValue eval(CMinusState state) throws Exception
    {
        CMinusValue rv = target.eval(state);
        if(!rv.type.equals("int")) throw new Exception("type mismatch");
        return new CMinusValue("int", -(Integer)rv.value);
    }
}
