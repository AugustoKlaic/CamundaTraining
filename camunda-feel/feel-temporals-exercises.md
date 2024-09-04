# Exercises
## Temporals

### Date
- Create a date representing your birthdate using the date function
  - ``date("1997-07-01")``
- Create a date with this year's Christmas Day using the "@" prefix 
  - ``@"2024-12-25"``

### Time
- Create the time you woke up this morning using the time function
  - ``time("07:45:00")``
- Create the time you usually have dinner using the "@" prefix and add the ZoneId of your
  - ``@"20:35:00@America/Sao_Paulo"``
 
### Date and Time
- Create a date and time representing the current time where you are, using the date and time function
  - ``date and time("2024-09-03T16:23:56")``
- Create a date and time representing the time you started this course using the "@" prefix
  - ``@"2024-08-17T20:38:44"``

### Duration
- Create a day-time duration for the days, hours, minutes and seconds you have been working this week. using the "@" prefix
  - ``@"P2DT16H30M10S"``
- Create a year-month duration for your current position at work using the duration function
  - ``duration("P7Y4M")``
- How would you create a duration for 10 months, 12 days and 3 seconds? Let's assume each month has 30 days.
  - ``duration("P312DT3S")``

### Addition
- Add 10 years and 6 months to your birthdate
  - ``date("1997-07-01") + duration("P10Y6M")``
- Add 45 minutes to 4:15 PM
  - ``time("16:15:00") + duration("PT45M")``
- Add 2 years, 3 months, and 5 hours to February 28, 2024, at 9:41 PM
  - ``date and time("2024-02-28T21:41:00") + duration("P820DT5H")`` 
- Add 2 hours, 18 minutes, and 36 seconds to 10:41:24 PM.
  - ``time("22:41:24") + duration("PT2H18M36S")``

### Subtraction
- Subtract 9 months to your birthdate
  - ``date("1997-07-01") - duration("P9M")``
- Subtract 45 minutes to 4:15 PM using time()
  - ``time("16:15:00") - duration("PT45M")``
- Subtract August 27, 2034 to August 28, 2034
  - ``date("2034-08-27") - date("2034-08-28")``
- Subtract 2 hours and 19 minutes to August 28, 2024, at 9:41 PM
  - ``date and time("2024-08-28T21:41:00") - @"PT2H19M"``
- Subtract 2 hours, 18 minutes, and 36 seconds to 10:41:24 PM using duration()
  - ``time("22:41:24") - duration("PT2H18M36S")``

### Multiplication
- Multiply 9 months by 3
  - ``duration("P9M") * 3``
- Multiply 2 days by 5
  - ``duration("P2D") * 5``

### Division
- Divide 12 months by 1 month
  - ``@"P12M" / @"P1M"``
- Divide 10 days by 5
  - ``duration("P10D") / 5``
- Divide 40 years by 1 month
  - ``duration("P40Y") / @"P1M"``
- Divide 1 year by 4
  - ``duration("P1Y") / 4``

### Attributes
- Get the weekday of your birthdate using date and time
  - ``date and time("1997-07-01").weekday``
- Get the minute of 4:15 PM using @
  - ``@"16:15:00".minute``
- Get the days of 5 days, 24 hours, 19 minutes and 34 seconds
  - ``duration("P5DT24H19M34S").days``
- Get the years of 36 months
  - ``duration("P36M").years``

### Functions
#### now()
- Subtract 1 day and 15 minutes from the current date and time
  - ``now() - duration("P1DT15M")``
- Double the time duration of 3 hours and add it to the current date and time
  - ``now() + (2 * duration("PT3H"))``
- Divide the time interval of 1 day by 2 and add it to the current date and time
  - ``now() + (duration("P1D") / 2)``
- Calculate your age using now() and your birthdate
  - ``now().year - date("1997-07-01").year``
- Calculate the local timezone for UTC+2
  - ``now() + duration("PT2H")``

#### today()
- Calculate your age using today() and your birthdate
  - ``today().year - date("1997-07-01").year``
- Calculate the days from today to 1 year using today()
  - ``today() - (today() + duration("P1Y")).days``

#### Day, Week, Month of
- Get the day of the week on which you were born
  - ``day of week(date("1997-07-01"))``
- Get the current day of the year
  - ``day of year(today())``