package com.github.xesam.locating.geo2district.files;

import com.github.xesam.locating.geo2district.Boundary;
import com.github.xesam.locating.geo2district.District;
import com.github.xesam.locating.geo2district.DistrictLoader;
import com.github.xesam.locating.geo2district.core.MultiPolygon;
import com.github.xesam.locating.geo2district.core.Polygon;
import com.github.xesam.gis.core.Coordinate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

/**
 * @author xesamguo@gmail.com
 */
public class FileDistrictLoader implements DistrictLoader {

    private File dataDir;

    public FileDistrictLoader(File dataDir) {
        this.dataDir = dataDir;
    }

    private File getAdCodeFile(String adcode) {
        return new File(dataDir, adcode + ".json");
    }

    @Override
    public Optional<District> load(String adcode) {
        File adcodeFile = getAdCodeFile(adcode);
        try (FileReader reader = new FileReader(adcodeFile)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Coordinate.class, new CoordinateDeserializer())
                    .registerTypeAdapter(MultiPolygon.class, new MultiPolygonDeserializer())
                    .registerTypeAdapter(Polygon.class, new PolygonDeserializer())
                    .create();
            GeoDistrict geoDistrict = gson.fromJson(reader, new TypeToken<GeoDistrict>() {
            }.getType());

            District district = new District();
            district.setAdcode(geoDistrict.properties.adcode);
            district.setName(geoDistrict.properties.name);
            district.setCenter(geoDistrict.properties.center);
            district.setBoundary(new Boundary(geoDistrict.geometry.geometry));
            return Optional.of(district);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
