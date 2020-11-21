package com.lc.user.controller;

import com.lc.user.constant.CookieConstant;
import com.lc.user.constant.RedisConstant;
import com.lc.user.dataobject.UserInfo;
import com.lc.user.enums.ResultEnum;
import com.lc.user.enums.RoleEnum;
import com.lc.user.service.UserService;
import com.lc.user.utils.CookieUtil;
import com.lc.user.utils.ResultVOUtil;
import com.lc.user.vo.ResultVO;
import com.oracle.javafx.jmx.SGMXBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登录控制类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/21
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家角色登录.
     * http://localhost:8007/login/buyer?openid=abc
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        // 根据openid和数据库里的数据是否匹配.
        UserInfo userInfo = userService.findByOpenid(openid);

        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 判断角色.
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // cookie里设置openid=abc.
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVOUtil.success();
    }

    /**
     * 卖家角色登录.
     * @param openid
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

        // 判断是否已经登录.
        if (cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,
                        cookie.getValue())))) {
            return ResultVOUtil.success();
        }

        // 根据openid和数据库里的数据是否匹配.
        UserInfo userInfo = userService.findByOpenid(openid);

        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 判断角色.
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // redis设置key=UUID, value=xyz.
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);

        // cookie里设置openid=abc.
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return ResultVOUtil.success();
    }
}
