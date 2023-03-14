package com.liyouling.patient_track.service.impl;

import com.liyouling.patient_track.dao.po.Messages;
import com.liyouling.patient_track.dao.MessagesMapper;
import com.liyouling.patient_track.service.MessagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 留言-回复表 服务实现类
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements MessagesService {

}
