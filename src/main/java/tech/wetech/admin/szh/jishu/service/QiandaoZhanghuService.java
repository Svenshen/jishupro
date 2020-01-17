/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.service;

import java.util.List;
import tech.wetech.admin.szh.jishu.domain.QiandaoLeibie;
import tech.wetech.admin.szh.jishu.domain.QiandaoZhanghu;



/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-7-5 8:13:16
 */
public interface QiandaoZhanghuService extends IService<QiandaoZhanghu, Long>{

    public List<QiandaoZhanghu> queryOne(QiandaoLeibie qiandaoLeibie);
    
    
}
