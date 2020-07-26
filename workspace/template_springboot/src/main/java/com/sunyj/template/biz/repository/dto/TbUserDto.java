package com.sunyj.template.biz.repository.dto;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author jia
 * @since 2020/7/26 20:22
 */
@Document(indexName="template",type="tb_user")
@Data
public class TbUserDto {
    private String id;
    private String username;
    private String email;
    private String phone;
    private LocalDate birthday;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
