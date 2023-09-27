package com.github.xesam.locating.geo2district;

import java.util.Optional;

public interface DistrictLoader {
    Optional<District> load(String adcode);
}
