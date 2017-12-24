package vo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by py on 2017/10/19.
 */
public class ClassifyVO {
    /**
     * 分类id
     */
    public int id;

    /**
     * 分类名称
     */
    public String name;

    /**
     * 分类下的商品
     */

    public Set<GoodsVO> goodsSet = new HashSet<GoodsVO>();

    public ClassifyVO() {
    }

    public ClassifyVO(String name, Set<GoodsVO> goodsSet) {
        this.name = name;
        this.goodsSet = goodsSet;
    }

    public ClassifyVO(int id, String name, Set<GoodsVO> goodsSet) {
        this.id = id;
        this.name = name;
        this.goodsSet = goodsSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GoodsVO> getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(Set<GoodsVO> goodsSet) {
        this.goodsSet = goodsSet;
    }
}
