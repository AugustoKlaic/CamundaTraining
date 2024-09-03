# Temporal

## Expressions
Creates a new temporal value

### Literal
- Syntax: temporal function = ``date("2024-04-03")`` or @notation = ``@"2024-04-03"``
- A value can be written in two ways, temporal function or using "@ notation"
- There are 4 different temporal functions: ``date``, ``time``, ``date and time``, ``duration``
- ``date and time("2020-04-06T08:00:00@Europe/Berlin")`` or ``duration("P1Y6M")`` or ``time("08:00:00+02:00")`` or ``@"2020-04-06"``

### Addition
- Adds a value to another value
- The addition can occur in 7 different ways, otherwise the result will be null
  - date + duration = date
  - time + days-time-duration = time
  - date-time + duration = date-time
  - duration + date = date
  - duration + time = time
  - duration + date-time = date-time
  - duration + duration = duration
- ``date("2020-04-06") + duration("P1D") = date("2020-04-07")`` or ``time("08:00:00") + duration("PT1H") = time("09:00:00")`` 
or ``date and time("2020-04-06T08:00:00") + duration("P7D") = date and time("2020-04-13T08:00:00")`` or ``duration("P2D") + duration("P5D") = duration("P7D")``

### Subtraction
- Subtracts a value from another value
- If a value contains a timezone or a time-offset, the other value must have it too, otherwise the result is null
- The subtraction can occur in 8 different ways, otherwise the result will be null
  - date + date = days-time-duration
  - date + duration = date
  - time + time = days-time-duration
  - time + days-time-duration = time
  - date-time + date-time = days-time-duration
  - date-time +	duration = date-time
  - days-time-duration + days-time-duration = days-time-duration
  - years-months-duration +	years-months-duration = years-months-duration
- ``date("2020-04-06") - date("2020-04-01") = duration("P5D")`` or ``date("2020-04-06") - duration("P5D") = date("2020-04-01")`` 
or ``time("08:00:00") - time("06:00:00") = duration("PT2H")`` or ``time("08:00:00") - duration("PT2H") = time("06:00:00")``
or ``duration("P7D") - duration("P2D") = duration("P5D")`` or ``duration("P1Y") - duration("P3M") = duration("P9M")``

### Multiplication
- Multiplies a value by another value
- The multiplication can occur in 4 ways, otherwise the result will be null
  - days-time-duration * number = days-time-duration
  - number * days-time-duration = days-time-duration
  - years-months-duration * number = years-months-duration
  - number * years-months-duration = years-months-duration
- ``duration("P1D") * 5 = duration("P5D")`` or ``duration("P1M") * 6 = duration("P6M")``

### Division
- Divides a value by another value
- The division can occur in 4 ways, otherwise the result will be null
  - days-time-duration / days-time-duration = number
  - days-time-duration / number = days-time-duration
  - years-months-duration / years-months-duration = number
  - years-months-duration / number = years-months-duration
- ``duration("P5D") / duration("P1D") = 5`` or ``duration("P5D") / 5 = duration("P1D")`` or ``duration("P1Y") / duration("P1M") = 12``
or ``duration("P1Y") / 12 = duration("P1M")``

### Properties
- Temporals have properties that can be accessed using the syntax: ``a.b``, where a is the temporal expression and the b is the property
    - year - date, date-time - the year as number
    - month - date, date-time - the month as number [1..12], where 1 is January
    - day - date, date-time - the day of the month as number [1..31]
    - weekday - date, date-time - the day of the week as number [1..7], where 1 is Monday
    - hour - time, date-time - the hour of the day as number [0..23]
    - minute - time, date-time - the minute of the hour as number [0..59]
    - second - time, date-time - the second of the minute as number [0..59]
    - time offset - time, date-time - the duration offset corresponding to the timezone or null
    - timezone - time, date-time - the timezone identifier or null
    - days - days-time-duration - the normalized days component as number
    - hours - days-time-duration - the normalized hours component as number [0..23]
    - minutes - days-time-duration - the normalized minutes component as number [0..59]
    - seconds - days-time-duration - the normalized seconds component as number [0..59]
    - years - years-months-duration - the normalized years component as number
    - months - years-months-duration - the normalized months component as number [0..11]
- ``date("2020-04-06").year = 2020`` or ``date("2020-04-06").month = 4`` or ``date("2020-04-06").weekday = 1`` 
or ``time("08:00:00").hour = 8`` or ``date and time("2020-04-06T08:00:00+02:00").time offset = duration("PT2H")`` 
or ``date and time("2020-04-06T08:00:00@Europe/Berlin").timezone = "Europe/Berlin"`` or ``duration("PT2H30M").hours = 2`` 
or ``duration("PT2H30M").minutes = 30`` or ``duration("P6M").months = 6``