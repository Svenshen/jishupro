/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.wetech.admin.szh.jishu.domain.QiandaoLeibie;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-7-5 15:33:02
 */
@Repository
public interface QiandaoLeibieDao extends  JpaRepository<QiandaoLeibie,Long>{

}
