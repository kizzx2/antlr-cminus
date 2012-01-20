package com.kizzx2.cminus;

public class CMinusConstant implements IEvaluator
{
    CMinusValue val;
    
    public CMinusConstant(String s)
    {
        val = new CMinusValue("string", s);
    }
    
    public CMinusConstant(Integer i)
    {
        val = new CMinusValue("int", i);
    }

    @Override
    public CMinusValue eval(CMinusState state) throws Exception
    {
        return val;
    }

}
