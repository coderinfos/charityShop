/**
 * coderinfo Inc.
 * <p>
 * Copyright (c) 201901-2020 All Rights Reserved.
 */
package org.greencode.modules.app.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.catalina.User;
import org.greencode.modules.app.entity.BossEntity;
import org.greencode.modules.app.entity.DonateEntity;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.BossService;
import org.greencode.modules.app.service.DonateService;
import org.greencode.modules.app.service.UserService;
import org.greencode.modules.app.vo.HomeDonateDTO;
import org.greencode.modules.app.vo.HomeSchedulingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fenggang
 * @version Id: HomeBiz.java, v 0.1 2020年01月06日
 * 14:25 fenggang Exp $
 */
@Service
public class HomeBiz {

    @Autowired
    private DonateService donateService;

    @Autowired
    private BossService bossService;

    @Autowired
    private UserService userService;

    /**
     * 获取最新排班数据
     *
     * @param num 数据条数
     * @return
     */
    public List<HomeSchedulingDTO> scheduling(int num) {
        //获取当前时间
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = startDateTime.plusDays(num);

        //根据num计算查询开始时间
        LambdaQueryWrapper<BossEntity> bossLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加时间查询条件
        bossLambdaQueryWrapper.between(BossEntity::getDutyDate,startDateTime,endDateTime);
        bossLambdaQueryWrapper.orderByAsc(BossEntity::getDutyDate);
        bossLambdaQueryWrapper.last(" limit "+num);

        List<BossEntity> bossEntityList = bossService.list(bossLambdaQueryWrapper);
        if (CollectionUtils.isEmpty(bossEntityList)) {
            return Lists.newArrayList();
        }


        // 获取用户数据
        Set<Long> userIdSet = bossEntityList.stream().map(BossEntity::getUserId).collect(Collectors.toSet());
        Map<Long,UserEntity> userEntityMap = this.getUserList(userIdSet);

        List<HomeSchedulingDTO> schedulingDTOList =
                bossEntityList.stream().map(HomeSchedulingDTO::getBossEntity).map(homeSchedulingDTO -> {
                    if(!Objects.isNull(homeSchedulingDTO.getDownUserId())){
                        UserEntity userEntity = userEntityMap.get(Long.valueOf(homeSchedulingDTO.getDownUserId()));
                        if(!Objects.isNull(userEntity)){
                            homeSchedulingDTO.setDownUsername(userEntity.getUserName());
                            if(userEntity.getFaceOpen()==1){
                                homeSchedulingDTO.setDownHead(userEntity.getFace());
                            }
                        }
                    }
                    if(!Objects.isNull(homeSchedulingDTO.getUpUserId())){
                        UserEntity userEntity = userEntityMap.get(Long.valueOf(homeSchedulingDTO.getUpUserId()));
                        if(!Objects.isNull(userEntity)){
                            homeSchedulingDTO.setUpUsername(userEntity.getUserName());
                            if(userEntity.getFaceOpen()==1) {
                                homeSchedulingDTO.setUpHead(userEntity.getFace());
                            }
                        }
                    }
                    return homeSchedulingDTO;
                }).collect(Collectors.toList());

        // 合并同一天的数据
        Map<String,List<HomeSchedulingDTO>> listMap = schedulingDTOList.stream().collect(Collectors.groupingBy(HomeSchedulingDTO::getDate));

        List<HomeSchedulingDTO> resultList = Lists.newArrayList();
        for(Map.Entry<String,List<HomeSchedulingDTO>> map:listMap.entrySet()){
            if(CollectionUtils.isEmpty(map.getValue())){
                continue;
            }
            HomeSchedulingDTO schedulingDTO = new HomeSchedulingDTO();
            schedulingDTO.setDate(map.getKey());
            for (HomeSchedulingDTO dto:map.getValue()) {
                if(!Objects.isNull(dto.getUpUserId())){
                    schedulingDTO.setUpUsername(dto.getUpUsername());
                }else if(!Objects.isNull(dto.getDownUserId())){
                    schedulingDTO.setDownUsername(dto.getDownUsername());
                }
            }
            resultList.add(schedulingDTO);
        }

        return resultList;
    }

    /**
     * @param num 数据条数
     * @return
     */
    public List<HomeDonateDTO> donate(int num) {
        LambdaQueryWrapper<DonateEntity> donateLambdaQueryWrapper = new LambdaQueryWrapper<>();
        donateLambdaQueryWrapper.orderByDesc(DonateEntity::getId);
        donateLambdaQueryWrapper.last(" limit " + num);
        List<DonateEntity> donateEntityList = donateService.list(donateLambdaQueryWrapper);
        if (CollectionUtils.isEmpty(donateEntityList)) {
            return Lists.newArrayList();
        }

        // 获取用户数据
        Set<Long> userIdSet = donateEntityList.stream().map(DonateEntity::getUserId).collect(Collectors.toSet());
        Map<Long,UserEntity> userEntityMap = this.getUserList(userIdSet);

        List<HomeDonateDTO> donateDTOList =
                donateEntityList.stream().map(HomeDonateDTO::getDonateEntity).map(homeDonateDTO -> {
                    UserEntity userEntity = userEntityMap.get(Long.valueOf(homeDonateDTO.getUserId()));
                    if(!Objects.isNull(userEntity)){
                        homeDonateDTO.setUsername(userEntity.getUserName());
                    }
                    return homeDonateDTO;
                }).collect(Collectors.toList());
        return donateDTOList;
    }

    private Map<Long,UserEntity> getUserList(Collection userIdCollection) {
        Collection<UserEntity> userEntities =  userService.listByIds(userIdCollection);
        if(CollectionUtils.isEmpty(userEntities)){
            return Maps.newConcurrentMap();
        }
        List<UserEntity> userEntityList =  Lists.newArrayList(userEntities);
        Map<Long,UserEntity> userEntityMap = userEntityList.stream().collect(Collectors.toMap(UserEntity::getId, userEntity -> userEntity));
        return userEntityMap;
    }
}
