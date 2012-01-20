package com.kizzx2.cminus;

import java.util.HashSet;
import java.util.Set;

public class CMinusBuiltInFunctions
{
    static HashSet<String> bifs = new HashSet<String>()
    {{
        add("print");
    }};
    
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
