package com.sunyj.template.biz.repository;

import com.sunyj.template.biz.repository.dto.TbUserDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author jia
 * @since 2020/7/26 20:48
 */
public interface TbUserRepository extends ElasticsearchRepository<TbUserDto, String> {
}
