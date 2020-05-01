#include "space_age.h"

namespace space_age {

long long space_age::seconds() const {
    return earth_seconds;
}

double space_age::on_mercury() const {
    return earth_years / 0.2408467;
}

double space_age::on_venus() const {
    return earth_years / 0.61519726;
}

double space_age::on_earth() const {
    return earth_years;
}

double space_age::on_mars() const {
    return earth_years / 1.8808158;
}

double space_age::on_jupiter() const {
    return earth_years / 11.862615;
}

double space_age::on_saturn() const {
    return earth_years / 29.447498;
}

double space_age::on_uranus() const {
    return earth_years / 84.016846;
}

double space_age::on_neptune() const {
    return earth_years / 164.79132;
}

}  // namespace space_age
