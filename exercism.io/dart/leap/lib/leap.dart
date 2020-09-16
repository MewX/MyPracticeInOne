class Leap {
  /// leapYear checks whether the input year is leap year or not.
  bool leapYear(int y) => y % 4 == 0 && (y % 100 != 0 || y % 400 == 0);
}
