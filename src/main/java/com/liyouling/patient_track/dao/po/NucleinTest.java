package com.liyouling.patient_track.dao.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 病人核酸检测报告表
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_nuclein_test")
public class NucleinTest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 病人ID
     */
    private String patientId;

    /**
     * 核酸检测报告
     */
    private String nucleinTestReport;

    /**
     * 是否可用
     */
    private Integer isValid;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;


}
