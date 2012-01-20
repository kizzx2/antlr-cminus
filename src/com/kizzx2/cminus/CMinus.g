grammar CMinus;

options
{
    language = Java;
    output   = AST;
}

tokens
{
    DEF;
    CALL;
    DEFUN;
    PARAMS;
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
      EOF!
    ;
    
declaration
    : type ID ';' -> ^(DEF type ID)
    ;
    
function
    : type ID LPAREN paramList? RPAREN
      OPEN_BRACE
      declaration*
      stmt*
      CLOSE_BRACE
    -> ^(DEFUN type ID ^(PARAMS paramList?) declaration* stmt*)
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
    : ('-'^)? term
    ;
    
multDivExpr
    : negationExpr (('*'|'/')^ negationExpr)?
    ;

addSubExpr
    : multDivExpr (('+'|'-')^ multDivExpr)?
    ;
    
type
    : 'int' | 'string'
    ;
    
paramList
    : type ID (COMMA type ID)* -> (type ID)+
    ;

callList
    : expression (COMMA expression)* -> expression+
    ;

fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment ENDL : '\r\n' | '\n' ;
fragment DIGIT : '0'..'9' ;

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