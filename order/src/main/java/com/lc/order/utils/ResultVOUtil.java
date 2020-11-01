package com.lc.order.utils;

import com.lc.order.vo.ResultVO;

/**
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);

        return resultVO;
    }
}
