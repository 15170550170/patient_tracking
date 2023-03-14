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
 * 病人医生预约表
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_reservation")
public class Reservation implements Serializable {

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
     * 医生ID
     */
    private String doctorId;

    /**
     * 预约金额
     */
    private String reservationAmonut;

    /**
     * 预约时间
     */
    private String reservationTime;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 是否审核
     */
    private String isVerify;

    /**
     * 审核回复
     */
    private String verifyReview;

    /**
     * 是否支付
     */
    private Boolean isPay;

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
