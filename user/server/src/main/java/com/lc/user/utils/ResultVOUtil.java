package com.lc.user.utils;

import com.lc.user.enums.ResultEnum;
import com.lc.user.vo.ResultVO;

/**
 * ResultVO工具类。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public class ResultVOUtil {

    /**
     * 返回成功信息.
     * @param object
     * @return
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("查询成功！");
        resultVO.setData(object);

        return resultVO;
    }

    /**
     * 返回成功信息.
     * @return
     */
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("登录成功！");

        return resultVO;
    }

    /**
     * 返回失败信息.
     * @param resultEnum
     * @return
     */
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMessage(resultEnum.getMessage());

        return resultVO;
    }
}
