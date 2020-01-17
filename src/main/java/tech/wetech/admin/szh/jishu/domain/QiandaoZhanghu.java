/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-7-5 8:04:36
 */
@Data
@Entity
public class QiandaoZhanghu implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @Column
    String username;
    
    @Column
    String password;
    
    @Column
    int pinlv;
    
    @Column
    String fujia ;
    
    @Column
    Long userid;
    
//    @Column
//    Long leibieid;
    
    @ManyToOne(fetch = FetchType.EAGER,optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name="leibieid")
    QiandaoLeibie qiandaoLeibie;
}
