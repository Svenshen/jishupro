/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.tasks;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2020-1-19 13:55:53
 */
public interface AsyncService<T> {
    /**
     * 执行异步任务
     * 
     */
    void executeAsync();
    
    void executeAsync(T t);
}
