package com.github.xesam.locating.geo2district.files;

import com.github.xesam.gis.core.Coordinate;
import com.google.gson.annotations.SerializedName;

class Properties {
    @SerializedName("name")
    public String name;
    @SerializedName("adcode")
    public String adcode;
    @SerializedName("center")
    public Coordinate center;
}
