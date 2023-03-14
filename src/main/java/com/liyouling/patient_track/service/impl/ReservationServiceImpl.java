package com.liyouling.patient_track.service.impl;

import com.liyouling.patient_track.dao.po.Reservation;
import com.liyouling.patient_track.dao.ReservationMapper;
import com.liyouling.patient_track.service.ReservationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 病人医生预约表 服务实现类
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements ReservationService {

}
