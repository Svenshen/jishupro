/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.wetech.admin.szh.jishu.dao.QiandaoZhanghuDao;
import tech.wetech.admin.szh.jishu.domain.QiandaoLeibie;
import tech.wetech.admin.szh.jishu.domain.QiandaoZhanghu;
import tech.wetech.admin.szh.jishu.service.QiandaoZhanghuService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-7-5 8:14:36
 */
@Service
public class QiandaoZhanghuServiceImpl extends BaseService<QiandaoZhanghu,Long> implements QiandaoZhanghuService{

    @Autowired
    QiandaoZhanghuDao qiandaoZhanghuDao;
    
    @Override
    public List<QiandaoZhanghu> queryOne(QiandaoLeibie qiandaoLeibie) {
        return qiandaoZhanghuDao.findByQiandaoLeibie(qiandaoLeibie);
        
    }

}
