/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.wetech.admin.szh.jishu.domain.QiandaoLeibie;
import tech.wetech.admin.szh.jishu.domain.QiandaoZhanghu;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-7-5 8:12:08
 */
@Repository
public interface QiandaoZhanghuDao extends  JpaRepository<QiandaoZhanghu,Long>{
    
    public List<QiandaoZhanghu> findByQiandaoLeibie(QiandaoLeibie qiandaoLeibie);
    
}
