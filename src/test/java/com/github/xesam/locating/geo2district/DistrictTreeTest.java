package com.github.xesam.locating.geo2district;

import com.github.xesam.gis.core.Coordinate;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

/**
 * @author xesamguo@gmail.com
 */
public class DistrictTreeTest {
    static DistrictTree districtTree;
    static DistrictLoader districtLoader;

    @BeforeClass
    public static void beforeClass() {
        districtTree = TestHelper.getDistrictTree();
        districtLoader = TestHelper.getDistrictLoader();
    }

    @Test
    public void getTreeByNameWuhan() {
        Optional<DistrictTree> sub = districtTree.getTreeByName("湖北省", "武汉市");

        Assert.assertTrue(sub.isPresent());
        DistrictTree wuhanTree = sub.get();
        District wuhan = wuhanTree.getDistrict();

        Assert.assertEquals("420100", wuhan.getAdcode());
        Assert.assertEquals("武汉市", wuhan.getName());
    }

    @Test
    public void getTreeByNameWuhanHongshan() {
        Optional<DistrictTree> sub = districtTree.getTreeByName("湖北省", "武汉市", "洪山区");

        Assert.assertTrue(sub.isPresent());
        DistrictTree hongshanTree = sub.get();
        District hongshan = hongshanTree.getDistrict();

        Assert.assertEquals("洪山区", hongshan.getName());
    }

    @Test
    public void getTreeByNameNotFound() {
        Optional<DistrictTree> sub = districtTree.getTreeByName("湖北省", "北京市");

        Assert.assertFalse(sub.isPresent());
    }

    @Test
    public void getWuhanTreeByPointInChina() {
        districtTree.inflateForDepth(districtLoader, 2);
        Optional<DistrictTree> wuhanTreeOptional = districtTree.getTreeByPoint(new Coordinate(114.305469, 30.593175));

        Assert.assertTrue(wuhanTreeOptional.isPresent());

        DistrictTree wuhanTree = wuhanTreeOptional.get();
        District wuhan = wuhanTree.getDistrict();

        Assert.assertEquals("武汉市", wuhan.getName());
    }

    @Test
    public void getWuhanTreeByPointInWuhan() {

        Optional<DistrictTree> subOptional = districtTree.getTreeByName("湖北省", "武汉市");
        Assert.assertTrue(subOptional.isPresent());

        DistrictTree sub = subOptional.get();
        sub.inflateForDepth(districtLoader, 0);
        Optional<DistrictTree> wuhanTreeOptional = sub.getTreeByPoint(new Coordinate(114.305469, 30.593175));
        Assert.assertTrue(wuhanTreeOptional.isPresent());

        DistrictTree wuhanTree = wuhanTreeOptional.get();
        District wuhan = wuhanTree.getDistrict();

        Assert.assertEquals("武汉市", wuhan.getName());
    }

    @Test
    public void getHongshanTreeByPointInWuhan() {

        Optional<DistrictTree> subOptional = districtTree.getTreeByName("湖北省", "武汉市");
        Assert.assertTrue(subOptional.isPresent());

        DistrictTree sub = subOptional.get();
        sub.inflateForDepth(districtLoader, 1);
        //华中科技大学 114.40776, 30.51415
        Optional<DistrictTree> hongshanOptional = sub.getTreeByPoint(new Coordinate(114.40776, 30.51415));
        Assert.assertTrue(hongshanOptional.isPresent());

        DistrictTree hongshanTree = hongshanOptional.get();
        District hongshan = hongshanTree.getDistrict();

        Assert.assertEquals("洪山区", hongshan.getName());
    }
}
