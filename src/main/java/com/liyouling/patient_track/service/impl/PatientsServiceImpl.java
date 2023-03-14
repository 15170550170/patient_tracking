package com.liyouling.patient_track.service.impl;

import com.liyouling.patient_track.dao.po.Patients;
import com.liyouling.patient_track.dao.PatientsMapper;
import com.liyouling.patient_track.service.PatientsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Service
public class PatientsServiceImpl extends ServiceImpl<PatientsMapper, Patients> implements PatientsService {

}
