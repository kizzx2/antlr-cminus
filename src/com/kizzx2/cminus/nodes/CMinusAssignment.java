package com.kizzx2.cminus.nodes;

public class CMinusAssignment implements IEvaluator
{
    String lval;
    IEvaluator rval;

    public CMinusAssignment(String lval, IEvaluator rval)
    {
        super();
        this.lval = lval;
        this.rval = rval;
    }

    @Override
    public CMinusValue eval(CMinusState state) throws Exception
    {
        String ltype = state.peekVariable(lval).type;
        CMinusValue result = rval.eval(state);

        if (!result.type.equals(ltype))
            throw new Exception("type mismatch");

        state.peekVariable(lval).value = result.value;

        // TODO Auto-generated method stub
        return state.peekVariable(lval);
    }

}
