grammar NestedNameLIst;
list :"[" elements "]";
element: element(,element)*;
element: NAME "=" NAME
				|Name
				|list;
Name ('a'..'z'|'A'..'Z')+;