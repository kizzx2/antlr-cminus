tree grammar CMinusTree;

options
{
    language     = Java;
    tokenVocab   = CMinus;
    ASTLabelType = CommonTree;
    output       = AST;
}

@header
{
package com.kizzx2.cminus.nodes;
import com.kizzx2.cminus.parser.*;

import java.util.HashMap;
}

program returns [CMinusProgram rv]
    : ^(PROGRAM declarations functions)
      { $rv = new CMinusProgram($declarations.rv, $functions.rv); }
    ;
    
declarations returns [ArrayList<CMinusDeclaration> rv]
    @init { $rv = new ArrayList<CMinusDeclaration>(); }
    : ^(DECLS (declaration { $rv.add($declaration.rv); })*)
    ;
 
declaration returns [CMinusDeclaration rv]
    : ^(DEF type=TYPE id=ID) { $rv = new CMinusDeclaration($type.text, $id.text); }
    ;
    
functions returns [ArrayList<CMinusFunction> rv]
    @init { $rv = new ArrayList<CMinusFunction>(); }
    : ^(FUNCS (function { $rv.add($function.rv); })*)
    ;
    
function returns [CMinusFunction rv]
    : ^(DEFUN type=TYPE id=ID params declarations stmts)
      { $rv = new CMinusFunction($type.text, $id.text, $params.rv, $declarations.rv, $stmts.rv); }
    ;
    
params returns [HashMap<String, String> rv]
    @init { $rv = new HashMap<String, String>(); }
    : ^(PARAMS (param { $rv.put($param.id, $param.type); })*)
    ;
    
param returns [String type, String id]
    : ^(t=TYPE i=ID) { $type = $t.text; $id = $i.text; }
    ;
    
stmts returns [ArrayList<IEvaluator> rv]
    @init { $rv = new ArrayList<IEvaluator>(); }
    : (stmt { $rv.add($stmt.rv); })*
    ;
    
stmt returns [IEvaluator rv]
    : ^('=' id=ID expr) { $rv = new CMinusAssignment($id.text, $expr.rv); }
    | ^('return' expr) { $rv = new CMinusReturn($expr.rv); }
    | expr { $rv = $expr.rv; }
    ;
    
expr returns [IEvaluator rv]
    @init { ArrayList<IEvaluator> callParams = new ArrayList<IEvaluator>(); }
    : ^('+' lhs=expr rhs=expr) { $rv = new CMinusAddition($lhs.rv, $rhs.rv); }
    | ^(CALL ID (e=expr { callParams.add($e.rv); })*)
          { $rv = new CMinusFunctionCall($ID.text, callParams); }
    | ^(NEGATE e=expr) { $rv = new CMinusNegation($e.rv); }
    | INT { $rv = new CMinusConstant(Integer.parseInt($INT.text)); }
    | STRING_LITERAL { $rv = new CMinusConstant($STRING_LITERAL.text); }
    | ID { $rv = new CMinusVariable($ID.text); }
    ;
