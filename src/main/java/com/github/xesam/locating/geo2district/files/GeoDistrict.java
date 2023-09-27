package com.github.xesam.locating.geo2district.files;

import com.google.gson.annotations.SerializedName;

class GeoDistrict {
    @SerializedName("properties")
    public Properties properties;
    @SerializedName("geometry")
    public Geometry geometry;
}
