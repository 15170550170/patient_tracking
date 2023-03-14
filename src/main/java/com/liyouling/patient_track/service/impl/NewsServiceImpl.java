package com.liyouling.patient_track.service.impl;

import com.liyouling.patient_track.dao.po.News;
import com.liyouling.patient_track.dao.NewsMapper;
import com.liyouling.patient_track.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻表 服务实现类
 * </p>
 *
 * @author liyouling
 * @since 2023-03-11
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

}
