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
 * 提问-回复表
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_chats")
public class Chats implements Serializable {

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
     * 管理员ID
     */
    private String adminId;

    /**
     * 询问内容
     */
    private String askContent;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 是否回复
     */
    private Boolean isReply;

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
