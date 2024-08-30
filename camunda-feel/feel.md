# FEEL

#### [FEEL playground](https://camunda.github.io/feel-scala/docs/playground/)

### What is FEEL?
 - FEEL stands for ``Friendly Enough Expression Language`` and is designed to be understandable by both business professionals 
 and developers. It is used in BPMN, DMN and Forms.

### Data types
FEEL has the following types:

- Null
  - Represents nothing, empty
  - Java Type: **NULL**
- Number
  - A whole or floating point number. 
  - Can be  negative
  - NaN (Not a Number), and negative and positive infinitive are represented as null
  - Java Type: **BigDecimal**
  - ``1`` or ``1.0`` or ``-1`` or ``11111.11111``
- String
  - A sequence of characters enclosed in double quotes
  - Can contain escaped characters and unicode characters
  - Java Type: **String**
  - ``"Hello World!"``
- Boolean
  - Is either true or false
  - Java Type: **Boolean**
  - ``true`` or ``false``
- Date
  - A date without a time component
  - Format: ``yyyy-MM-dd``
  - Java Type: **LocalDate**
  - ``date(2024-04-03)`` or ``@"2024-03-04"``
- Time
  - Local zoned time. 
  - Can have an offset or timezone id
  - Java Type: **LocalTime** or **OffsetTime**
  - ``time("11:45:30")`` or ``time("13:30")`` or ``time("11:45:30+02:00")`` or ``time("10:31:10@Europe/Paris")`` or 
  ``@"11:45:30"`` or ``@"13:30"`` or ``@"11:45:30+02:00"`` or ``@"10:31:10@Europe/Paris"``
- Date-Time
  - A date with a local or zoned time component. 
  - Time can have an offset or time zone id
  - Java Type: **LocalDateTime** or **DateTime**
  - ``date and time("2015-09-18T10:31:10")``or ``date and time("2015-09-18T10:31:10+01:00")`` or ``date and time("2015-09-18T10:31:10@Europe/Paris")`` or``@"2015-09-18T10:31:10"`` or``@"2015-09-18T10:31:10+01:00"`` or``@"2015-09-18T10:31:10@Europe/Paris"``
- Days-time-duration
  - A duration based on seconds. 
  - It can contain days, hours, minutes and seconds
  - Format: ``PxDTxHxMxS`` 
  -  How I understood this formula: **P** stands for *PERIOD*, then you set the number of days followed by the **D** that stands for *DAYS*. 
After that you put the **T** to start writing the *TIME*. Then set the number of *HOURS* followed by the **H**, then the *MINUTES* followed by the **M** 
and then the *SECONDS* followed by the **S**
  - Java Type: **Duration**
  - ``duration("P4D")`` or ``duration("PT2H")`` or ``duration("PT30M")`` or ``duration("P1DT6H")`` or ``@"P4D"`` or ``@"PT2H"`` or ``@"PT30M"`` or ``@"P1DT6H"`` 
- Years-month-duration
  - A duration based on the calendar. 
  - It can contain years and months.
  - Format: ``PxYxM``
  - How I understood this formula: **P** stands for *PERIOD*, then you set the number of *YEARS* followed by **Y**, 
then set the number of **MONTHS** followed by the **M**
  - Java Type: **Period**
  - ``duration("P2Y")`` or ``duration("P6M")`` or ``duration("P1Y6M")`` or ``@"P2Y"`` or ``@"P6M"`` or ``@"P1Y6M"`` 
- List
  - A list of elements. 
  - Elements can be of any type. 
  - The list can be empty
  - Java Type: **List**
  - ``[]`` or ``[1,2,3]`` or ``["a","b"]`` or ``[["list"], "of", [["lists"]]]`` 
- Context
  - A list of entries. 
  - Each entry has a key and a value. 
  - The key is either a name or a string. 
  - The value can be any type. 
  - The context can be empty
  - Java Type: **Map**
  - ``{}`` or ``{a:1}`` or ``{b: 2, c: "valid"}`` or ``{nested: {d: 3}}`` or ``{"a": 1}`` or ``{"b": 2, "c": "valid"}``