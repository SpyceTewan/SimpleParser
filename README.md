# SimpleParser
A simple file format to read Strings from a file using keys
# File Format:
```
exampleKey: exampleValue
         <-- Needs to have an empty line at the end!!!
```
# Code usage:
```Java
SP.getValue(file, key); //Returns the value of the key
```
