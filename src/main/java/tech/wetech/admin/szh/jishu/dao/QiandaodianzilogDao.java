/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.wetech.admin.szh.jishu.domain.Qiandaodianzilog;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2020-1-19 13:09:09
 */
@Repository
public interface QiandaodianzilogDao extends  JpaRepository<Qiandaodianzilog,String>{

}
