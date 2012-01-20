package com.kizzx2.cminus.nodes;

public class CMinusReturn implements IEvaluator
{
    IEvaluator rval;

    public CMinusReturn(IEvaluator rval)
    {
        super();
        this.rval = rval;
    }

    @Override
    public CMinusValue eval(CMinusState state) throws Exception
    {
        return rval.eval(state);
    }
}
