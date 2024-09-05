# Exercises
## Lists

### DataTypes
- Create a list of the names of the planets in the solar system
  - ``["mercury", "venus", "earth", "mars", "jupiter", "saturn", "uranus", "neptune"]``
- Create a list of numbers from 1 to 8
  - ``[1, 2, 3, 4, 5, 6, 7, 8]``
- Create a list including 2 lists: One list includes my favorite numbers 3.14, 7 and 42 and a second list should include your favorite numbers
  - ``"[[3.14, 7, 42], [2, 7, 9]]"``

### Expressions
- Given this list, ``["Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"]``, get the second last element
  - ``["Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"][-2]``
- Given this list, ``[1,2,3,4]``, get the elements that are even. You can use the even() function
  - ``[1,2,3,4][even(item)]``
- Given this list, ``[10,20,30,40]``, get the elements different to 20
  - ``[10,20,30,40][item != 20]`` or ``[10,20,30,40][not(item = 20)]``
- Given two lists of numbers, ``[1,2]`` and ``[2,3]``, write a FEEL expression to check if there is at least one pair of elements x from the first list and y from the second list such that x is less than y.
  - ``some x in [1,2], y in [2,3] satisfies x < y``
- Check if all the elements of ``[10,20,30,40]`` are even. You can use the modulo(dividend,divisor) function
  - ``every x in [10,20,30,40] satisfies (modulo(x, 2) = 0)``

### Sum and Product
- Calculate the product of the elements in the list ``[1.5, -2, 3, -3]``
  - ``product([1.5, -2, 3, -3])``
- Calculate the sum of elements greater than 5 in the list ``[4, 5, 6, 7, 8]``
  - ``sum([4, 5, 6, 7, 8][item > 5])``

### Mean, Median, Standard Deviation, Mode
- Calculate the mean of the elements in the list ``[0, 1, 1, 2, 3, 5, 8, 13]``
  - ``mean([0, 1, 1, 2, 3, 5, 8, 13])``
- Calculate the median of the elements in the list ``[0, 1, 1, 2, 3, 5, 8, 13]``
  - ``median([0, 1, 1, 2, 3, 5, 8, 13])``
- Calculate the standard deviation of the elements in the list ``[0, 1, 1, 2, 3, 5, 8, 13]``
  - ``stddev([0, 1, 1, 2, 3, 5, 8, 13])``
- Find the mode of the numbers in the list ``[10, 20, 10, 30, 20, 30]``
  - ``mode([10, 20, 10, 30, 20, 30])``
- Find the mode of the numbers in the list ``[6, 7, 8, 9]``
  - ``mode([6, 7, 8, 9])``

### Min, Max
- Find the smallest value in alphabetical order in the list ``["apple", "banana", "cherry", "date"]``
  - ``min(["apple", "banana", "cherry", "date"])``
- Find the earliest date in the list ``[date("2022-01-01"), date("2021-12-31"), date("2023-07-15")]``
  - ``min([date("2022-01-01"), date("2021-12-31"), date("2023-07-15")])``

### Contrains
- Check if the string "grape" is in the list ``["apple", "banana", "grape", "melon"]``
  - ``list contains(["apple", "banana", "grape", "melon"], "grape")``
- Check if ``[1,2]`` is included in the list ``["apple", 7, [1,2]]``
  - ``list contains(["apple", 7, [1,2]], [1,2])``

### Count
- Count the number of elements in the list ``[true, false, true, false, null]``
  - ``count([true, false, true, false, null])``
- Count the number of even elements in the list ``[10, 21, 35, 40]``
  - ``count([10, 21, 35, 40][even(item)])``
- Count the number of string elements in the list ``[1, "apple", true, "banana", 5.5, "cherry"]``. You can use the instance of expression.
  - ``count([1, "apple", true, "banana", 5.5, "cherry"][item instance of string])``

### All and Any
- Determine if any element in the list ``[1.5, "summer", true, null]`` is false
  - ``all([1.5, "summer", true, null])``
- Determine if any element in the list ``[1.5, "summer", true, null]`` is true
  - ``any([1.5, "summer", true, null])``

### Sublist
- Extract elements from index 2 to 4 from the list ``[5, 10, 15, 20, 25, 30]``
  - ``sublist([5, 10, 15, 20, 25, 30], 2, 3)``
- Extract the last 2 elements from the list ``[5,12,9,17,3,14,1,6,11,18,8,4,7,15,10,2,19,13,16,20]``
  - ``sublist([5,12,9,17,3,14,1,6,11,18,8,4,7,15,10,2,19,13,16,20], -2, 2)``

### Append, Insert before, Remove
- Initialise an empty list with the following values using append(): 2, "Mars", true
  - ``append([], 2, "mars", true)``
- Insert "Earth" before "Mars" in the list ``["Mercury", "Venus", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"]``
  - ``insert before(["Mercury", "Venus", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"], 3, "Earth")``
- Remove "Uranus" from the list ``["Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"]``
  - ``remove(["Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"], -2)``

### Reverse and Index of
- Reverse the list ``["first", "second", "third", "fourth"]``
  - ``reverse(["first", "second", "third", "fourth"])``
- Find the index of "third" in the list ``["first", "second", "third", "fourth", "third", "fifth"]``
  - ``index of(["first", "second", "third", "fourth", "third", "fifth"], "third")``

### Union, Distinct values, Duplicate values
- Return a list combination of these lists without any duplicate values: ``[1,2]``, ``[2,3]``, and ``[3,4]``
  - ``union([1,2], [2,3], [3,4])`` -> union already eliminate duplicates
- Find the distinct values in the list ``[1, "1", 2, "2", 1, "2"]``
  - ``distinct values([1, "1", 2, "2", 1, "2"])``
- Find the duplicate values in the list ``["dog", "cat", "rat", "cow", "dog", "mouse", "dog", "eagle", "cat", "rat", "dog"]``
  - ``duplicate values(["dog", "cat", "rat", "cow", "dog", "mouse", "dog", "eagle", "cat", "rat", "dog"])``

### Sort
- Sort the list ``["elephant", "cat", "horse", "dog"]`` by the length of the strings. You can use the string length() function
  -  ``sort(["elephant", "cat", "horse", "dog"], function(x, y) string length(x) < string length(y)) ``
- Sort these dates in chronological order: ``["2023-12-01", "2024-12-01", "2021-12-01"]``
  - ``sort(["2023-12-01", "2024-12-01", "2021-12-01"], function(x, y) x < y)``
- Sort the list ``["pear", "plum", "apple", "banana"]`` by the second character of each string. You can use the substring() function
  - ``sort(["pear", "plum", "apple", "banana"], function(x, y) substring(x, 2, 1) < substring(y, 2, 1))``

### Flatten, Concatenate and String Join
- Flatten the nested list: ``[[1, 2], 3, 4, [5, 6]]``
  - ``flatten([[1, 2], 3, 4, [5, 6]])``
- Concatenate these lists ``[["car", "house"], [30, 40]]``, ``[true, true, false]``, and ``[[],[1]]``
  - ``concatenate([["car", "house"], [30, 40]], [true, true, false], [[],[1]])``
- Flatten the result of the previous concatenate() expression
  - ``flatten(concatenate([["car", "house"], [30, 40]], [true, true, false], [[],[1]]))``
- Build a paragraph in HTML (<p> tag) to display the colors of this list separated by comma ",": ``["red","yellow","blue","green"]``
  - ``string join(["red","yellow","blue","green"], ",", "<p>", "</p>")``