package com.lc.product.controller;

import com.lc.product.common.DecreaseStockInput;
import com.lc.product.common.ProductInfoOutput;
import com.lc.product.dataobject.ProductCategory;
import com.lc.product.dataobject.ProductInfo;
import com.lc.product.service.CategoryService;
import com.lc.product.service.ProductService;
import com.lc.product.utils.ResultVOUtil;
import com.lc.product.vo.ProductInfoVO;
import com.lc.product.vo.ProductVO;
import com.lc.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有在架的商品.
     * 获取类目type列表
     * 查询类目
     * 构造数据
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list(HttpServletRequest request) {

        // 查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        // 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                // 相同类目的商品加入到ProductInfoVO中
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

    /**
     * 查询商品列表(为订单微服务提供).
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) throws InterruptedException {

        // 测试hystrix的过期时间.
        Thread.sleep(2000);

        return productService.findList(productIdList);
    }

    /**
     * 扣库存(为订单微服务提供).
     * @param decreaseStockInputList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productService.decreaseStock(decreaseStockInputList);
    }

}
