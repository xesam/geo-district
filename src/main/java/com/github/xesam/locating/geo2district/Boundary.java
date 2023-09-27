package com.github.xesam.locating.geo2district;

import com.github.xesam.gis.core.Coordinate;
import com.github.xesam.gis.core.Relation;
import com.github.xesam.locating.geo2district.core.MultiPolygon;
import com.github.xesam.locating.geo2district.core.Polygon;

/**
 * 边界集合，一个行政区可能有多个不接壤的边界
 *
 * @author xesamguo@gmail.com
 */
public class Boundary {

    private final MultiPolygon polygons;

    public Boundary(MultiPolygon polygon) {
        this.polygons = polygon;
    }

    public Relation relationOf(Coordinate coordinate) {
        for (Polygon polygon : polygons.getCoordinates()) {
            Relation relation = polygon.relationOf(coordinate);
            if (relation == Relation.ON || relation == Relation.IN) {
                return relation;
            }
        }
        return Relation.OUT;
    }

}
