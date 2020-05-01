#include "space_age.h"

namespace space_age {

long long space_age::seconds() const {
    return SECONDS;
}

double space_age::on_mercury() const {
    return YEARS / 0.2408467;
}

double space_age::on_venus() const {
    return YEARS / 0.61519726;
}

double space_age::on_earth() const {
    return YEARS;
}

double space_age::on_mars() const {
    return YEARS / 1.8808158;
}

double space_age::on_jupiter() const {
    return YEARS / 11.862615;
}

double space_age::on_saturn() const {
    return YEARS / 29.447498;
}

double space_age::on_uranus() const {
    return YEARS / 84.016846;
}

double space_age::on_neptune() const {
    return YEARS / 164.79132;
}

}  // namespace space_age
