package com.liyouling.patient_track.service.impl;

import com.liyouling.patient_track.dao.po.Chats;
import com.liyouling.patient_track.dao.ChatsMapper;
import com.liyouling.patient_track.service.ChatsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 提问-回复表 服务实现类
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Service
public class ChatsServiceImpl extends ServiceImpl<ChatsMapper, Chats> implements ChatsService {

}
