#include "triangle.h"

namespace triangle {

flavor kind(double a, double b, double c) {
    const double precision = 1e-7;

    if (a < precision || b < precision || c < precision) {
        throw std::domain_error("non-positive rectangle side length");
    }

    // Not a triangle.
    if (a + b < c - precision || a + c < b - precision || b + c < a - precision) {
        throw std::domain_error("not a triangle");
    }

    if (std::abs(a - b) < precision && std::abs(b - c) < precision) {
        return flavor::equilateral;
    }

    if (std::abs(a - b) < precision|| std::abs(a - c) < precision  || std::abs(b - c) < precision) {
        return flavor::isosceles;
    }

    return flavor::scalene;
}

}  // namespace triangle
