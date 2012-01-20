package com.kizzx2.cminus;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CMinusState
{
    Map<String, Stack<CMinusValue>> variables = new HashMap<>();
    public Stack<CMinusValue> stack = new Stack<>();
    
    public CMinusValue popVariable(String id)
    {
        return variables.get(id).pop();
    }
    
    public CMinusValue peekVariable(String id)
    {
        return variables.get(id).peek();
    }
    
    public void pushVariable(String id, CMinusValue val)
    {
        if(!variables.containsKey(id))
            variables.put(id, new Stack<CMinusValue>());
        
        variables.get(id).push(val);
    }
}
