package com.github.xesam.locating.geo2district.files;

import com.github.xesam.locating.geo2district.core.MultiPolygon;
import com.google.gson.annotations.SerializedName;

/**
 * @author xesamguo@gmail.com
 */
class Geometry {
    @SerializedName("type")
    public String type;
    @SerializedName("coordinates")
    public MultiPolygon geometry;
}
