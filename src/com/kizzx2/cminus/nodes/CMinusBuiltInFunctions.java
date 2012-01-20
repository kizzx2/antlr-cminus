package com.kizzx2.cminus.nodes;

import java.util.HashSet;

public class CMinusBuiltInFunctions
{
    static HashSet<String> bifs = new HashSet<String>();
    
    static
    {
        bifs.add("print");
    }
    
    public static boolean isBIF(String id)
    {
        return bifs.contains(id);
    }
    
    public static CMinusValue call(String id, CMinusState state) throws Exception
    {
        switch(id)
        {
        case "print": return print(state);
        default:
            throw new Exception("Unrecognized built-in function: " + id);
        }
    }

    static CMinusValue print(CMinusState state)
    {
        CMinusValue val = state.stack.pop();
        System.out.println(val.value);
        return new CMinusValue("void", null);
    }
}
