#include "reverse_string.h"

namespace reverse_string {
	std::string reverse_string(std::string s) {
		std::reverse(s.begin(), s.end());
		return s;
	}
}  // namespace reverse_string
