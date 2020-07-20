package com.me.template.base;

import lombok.Data;

import java.util.Collection;

/**
 * 通用分页返回类
 * @author jia
 * @since 2020/1/11 16:46
 */
@Data
public class CommonPage<T> {
    private Long total;     // 总记录数
    private Integer pages;     // 总页数
    private Integer pageSize;     // 每页数据大小
    private Integer pageNum;    // 当前页
    private Collection<T> rows;         // 分页数据
}
