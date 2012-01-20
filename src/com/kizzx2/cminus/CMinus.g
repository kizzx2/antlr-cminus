grammar CMinus;

options
{
    language = Java;
    output   = AST;
}

tokens
{
    PROGRAM;
    DEF;
    CALL;
    DEFUN;
    PARAMS;
    DECLS;
    FUNCS;
    NEGATE;
}

@header
{
package com.kizzx2.cminus.parser;
}

@lexer::header
{
package com.kizzx2.cminus.parser;
}

program
    : declaration*
      function*
      EOF
    -> ^(PROGRAM ^(DECLS declaration*) ^(FUNCS function*))
    ;
    
declaration
    : TYPE ID ';' -> ^(DEF TYPE ID)
    ;
    
function
    @init { System.out.println("got a function"); }
    : TYPE ID LPAREN paramList? RPAREN
      OPEN_BRACE
      declaration*
      stmt*
      CLOSE_BRACE
    -> ^(DEFUN TYPE ID ^(PARAMS paramList?) ^(DECLS declaration*) stmt*)
    ;
 
stmt
    : assignmentStmt
    | returnStmt
    | expression SEMI!
    ;
    
assignmentStmt
    : ID '='^ expression SEMI!
    ;
    
returnStmt
    : 'return'^ expression SEMI!
    ;
    
expression
    : addSubExpr
    ;
    
functionCall
    : ID LPAREN callList? RPAREN -> ^(CALL ID callList?)
    ;
    
term
    : INT
    | STRING_LITERAL
    | functionCall
    | LPAREN! expression RPAREN!
    | ID
    ;
    
negationExpr
    : '-' term -> ^(NEGATE term)
    | term
    ;
    
multDivExpr
    : negationExpr (('*'|'/')^ negationExpr)?
    ;

addSubExpr
    : multDivExpr (('+'|'-')^ multDivExpr)?
    ;
    
paramList
    : TYPE ID (COMMA TYPE ID)* -> ^(TYPE ID)+
    ;

callList
    : expression (COMMA expression)* -> expression+
    ;

fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment ENDL : '\r\n' | '\n' ;
fragment DIGIT : '0'..'9' ;

TYPE : 'int' | 'string' ;
WS : (' ' | '\t' | '\n' | '\r\n')+ { $channel = HIDDEN; } ;
COMMENT : '//' .* ENDL { $channel = HIDDEN; } ;
ID : (LETTER | '_')(LETTER | '_' | DIGIT)* ;
COMMA : ',' ;
SEMI : ';' ;
LPAREN : '(' ;
RPAREN : ')' ;
OPEN_BRACE : '{' ;
CLOSE_BRACE : '}' ;
STRING_LITERAL : '"' ~('"' | '\\"' )* '"' ;


INT : '0' | ('1'..'9') DIGIT* ;