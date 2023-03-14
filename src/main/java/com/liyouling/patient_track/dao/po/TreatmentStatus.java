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
 * 
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_treatment_status")
public class TreatmentStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 当前状况
     */
    private String currentSituation;

    /**
     * 治疗状况
     */
    private String treatmentStatus;

    /**
     * 主治医生
     */
    private String attendingDoctor;

    /**
     * 诊断结果
     */
    private String diagnosticResult;

    /**
     * 心理状况
     */
    private String mentalStatus;

    /**
     * 护理状况
     */
    private String nursingStatus;

    /**
     * 病人满意度
     */
    private String patientSatisfaction;

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
