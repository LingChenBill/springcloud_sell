package com.lc.product.utils;

import com.lc.product.vo.ResultVO;

/**
 * ResultVO工具类。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("查询成功！");
        resultVO.setData(object);

        return resultVO;
    }
}
