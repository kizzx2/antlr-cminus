package com.kizzx2.cminus.nodes;

public interface IEvaluator
{
    CMinusValue eval(CMinusState state) throws Exception;
}
