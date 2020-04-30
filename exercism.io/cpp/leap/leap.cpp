#include "leap.h"

namespace leap {
	bool is_leap_year(int year) {
		if (year <= 0) {
			return false;
		}

		return year % 400 == 0 || (year % 4 ==0 && year % 100 != 0);
	}
}  // namespace leap
