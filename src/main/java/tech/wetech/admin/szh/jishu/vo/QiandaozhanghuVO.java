/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-12 15:00:26
 */
@Data
@AllArgsConstructor
public class QiandaozhanghuVO {
    
    Long id;
    
    String username;
    
    String password;
    
    int pinlv;
    
    String fujia ;
    
    String qiandaoLeibie;
    
   
}
