# Space-Cadets #

### Challenge 1: ###
Using a webservice to get a person's name based on their email id from a webpage containing people's email ids and names through the HTML.


### Challenge 2: ###
Building a bare bones interpreter based on the operations:
- `clear X;` - setting a variable X to a value of 0
- `incr X;` - increasing the value of a variable by 1
- `decr X;` - decreasing the value of a variable by 1
- `while X not 0 do;` - a loop that runs continuously until the variable X has a value of 0
- `end;` - the end of the section that is in the loop --> indicating that the loop has reached its end

Assumptions in my solution:
- No syntax errors
- All variables used are first declared using the `clear` operation before being used elsewhere (e.g. in a while loop).


### Challenge 3: ###
Extending the bare bones interpreter created in Challenge 2 to have more operations.

My extensions:
__1. Adding an n functionality to `while`, `incr` and `decr`:__

Syntax:
- `while X not n do` - loops until the value of the variable X is n, at which point its stops looping, rather than when X reaches 0.
- `incr X by n` - increases the value of the variable X by the value of n, rather than 1.
- `decr X by n` - decreases the value of the varaible X by the value of n, rather than 1.

Examples:
  - `while X not 5 do;`
  - `incr X by 10;`
  - `decr X by 3;`
       
__2. Adding a `set` operation, allowing the user to change the value of a variable quickly:__

Syntax:
- `set X to n` - changes the value of the variable X to the value of n.
- `set X to Y` - changes the value of the variable X to the value of the variable Y.
- `set X to X * n` - changes the value of the variable X, mulitplying it by n (possible operators: +, -, *, /).
- `set X to Y * n` - changes the value of the variable X to the value of the variable Y mulitplied by n (possible operators: +, -, *, /).
- `set X to X * Y` - changes the value of the variable X to the value of X * Y (possible operators: +, -, *, /).

Examples:
  - `set X to 15;`
  - `set X to X / 3;`
  - `set X to Y - 10;`
  - `set X to Y * 10;`
  
Note on the syntax:

The interpreter requires whitespace between the variable, the operation and the value of the operation.
The syntax `set X to Y/2` will not be interpreted correctly, whereas `set X to Y / 2` will be interpreted correctly.

__3. Adding `if` statements, allowing the user to carry out operations if a condition is true, otherwise carrying out other operations through an `else`:__

Syntax:
- 'if' with 'else'
```
if X is n do;
  // carry out other operations
else do;
  // carry out other operations
end if;
```
Carries out operations if the condition of X being of a value n is true, otherwise carries out other operations if the condition is not satisfied. The 'else do' is the point at which the first block of commands of the 'if' stops. The 'end if' is the point at which the second block of commands of the 'else' stops.

- 'if' without 'else'
```
if X is n do;
 // carry out other operations
end if;
```

Carries out operations if the condition of X being of a value n is true. The 'end if' is the point at which the second block of commands of the 'if' stops.

__4. Change to the syntax of `end` for a while loop:__

Changed the syntax of the ending of a while loop, previously: `end;` to now `end while;` to distinguish between the ending of an if statement and a while loop.
