package com.lovo.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by YBM on 2020/7/28 21:41
 */
@Data
@AllArgsConstructor
public class DBResult {
    /**
     * 修改影响行数
     */
    private int affectRows;
    /**
     * 新增影响行数
     */
    private int generatedKey;
}
