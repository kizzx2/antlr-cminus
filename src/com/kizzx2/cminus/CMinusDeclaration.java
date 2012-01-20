package com.kizzx2.cminus;

public class CMinusDeclaration
{
    String type;
    String id;

    public CMinusDeclaration(String type, String id)
    {
        super();
        this.type = type;
        this.id = id;
    }

    public void enter(CMinusState state)
    {
        state.pushVariable(id, new CMinusValue(type, null));
    }
    
    public void exit(CMinusState state)
    {
        state.popVariable(id);
    }
}
