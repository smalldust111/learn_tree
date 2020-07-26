package com.sunyj.template.biz.service.impl;

import com.sunyj.template.entity.TbUser;
import com.sunyj.template.biz.mapper.TbUserMapper;
import com.sunyj.template.biz.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sunyj
 * @since 2020-07-26
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

}
