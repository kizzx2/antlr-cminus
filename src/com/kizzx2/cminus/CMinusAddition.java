package com.kizzx2.cminus;

public class CMinusAddition implements IEvaluator
{
    IEvaluator lhs, rhs;

    public CMinusAddition(IEvaluator lhs, IEvaluator rhs)
    {
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public CMinusValue eval(CMinusState state) throws Exception
    {
        CMinusValue lval = lhs.eval(state);
        CMinusValue rval = rhs.eval(state);
        
        if(!lval.type.equals("int")) throw new Exception("type mismatch");
        if(!rval.type.equals("int")) throw new Exception("type mismatch");
        
        return new CMinusValue("int", (Integer)lval.value + (Integer)rval.value);
    }

}
