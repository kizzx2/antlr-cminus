# Interpreter for a small subset of C-Minus using ANTLR

Demonstrating:

- How to properly collect a list of values from productions using the `@init` code block.
- Finally a real life example of the totally undocumented "Tree Grammar".
- Rewrite rules in parser grammar.

You may think that the use of tree grammar is contrived since I did it the "manual" way of defining a separate class for every node. IMO this is pretty nice since now that the tree grammar now does the dirty work of creating AST nodes, the parser grammar is now clean and simple.

There are quite a lot of typing involved for the node classes (and that tree grammar largely duplicates parser grammar) but I really don't understand the hate for it -- do people expect to stuff everything in the parser/tree grammar when they deal with a big proper language?
