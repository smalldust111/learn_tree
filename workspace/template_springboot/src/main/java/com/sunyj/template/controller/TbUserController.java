package com.sunyj.template.controller;


import com.sunyj.template.base.beans.CommonResult;
import com.sunyj.template.biz.repository.TbUserRepository;
import com.sunyj.template.biz.repository.dto.TbUserDto;
import com.sunyj.template.biz.service.impl.TbUserServiceImpl;
import com.sunyj.template.entity.TbUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author sunyj
 * @since 2020-07-26
 */
@RestController
@RequestMapping("/tbUser")
public class TbUserController {
    @Autowired
    TbUserServiceImpl tbUserService;
    @Autowired
    TbUserRepository tbUserRepository;

    @PostMapping("/add")
    @Transactional
    public CommonResult add() {
        TbUser user = new TbUser();
        user.setPhone("111");
        user.setUsername("111");
        tbUserService.save(user);
        TbUserDto userDto = new TbUserDto();
        BeanUtils.copyProperties(user, userDto);
        tbUserRepository.save(userDto);
        System.out.println(user);
        throw new RuntimeException("");
        // return CommonResult.ok();
    }

    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable String id) {
        Optional<TbUserDto> byId = tbUserRepository.findById(id);
        TbUserDto userDto = byId.get();
        return CommonResult.ok(userDto);
    }
}

