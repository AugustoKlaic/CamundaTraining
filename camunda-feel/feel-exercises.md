# Exercises

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