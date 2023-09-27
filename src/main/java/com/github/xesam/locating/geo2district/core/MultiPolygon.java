package com.github.xesam.locating.geo2district.core;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author xesamguo@gmail.com
 */
public class MultiPolygon {
    @SerializedName("coordinates")
    private List<Polygon> coordinates;

    public MultiPolygon(List<Polygon> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Polygon> getCoordinates() {
        return coordinates;
    }
}
