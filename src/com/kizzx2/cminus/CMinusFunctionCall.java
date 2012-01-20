package com.kizzx2.cminus;

import java.util.ArrayList;
import java.util.ListIterator;

public class CMinusFunctionCall implements IEvaluator
{
    ArrayList<IEvaluator> params;
    String func;
    
    public CMinusFunctionCall(String func, ArrayList<IEvaluator> params)
    {
        super();
        this.func = func;
        this.params = params;
    }

    @Override
    public CMinusValue eval(CMinusState state) throws Exception
    {
        ListIterator<IEvaluator> li = params.listIterator(params.size());
        while(li.hasPrevious())
            state.stack.push(li.previous().eval(state));
        
        if(CMinusBuiltInFunctions.isBIF(func))
            return CMinusBuiltInFunctions.call(func, state);
        
        CMinusValue f = state.peekVariable(func);
        if(!f.type.equals("function")) throw new Exception("not a function");
        
        return ((IEvaluator)f.value).eval(state);
    }

}
