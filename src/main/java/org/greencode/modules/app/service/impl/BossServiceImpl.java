package org.greencode.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.greencode.common.utils.R;
import org.greencode.modules.app.dao.ShopDao;
import org.greencode.modules.app.dao.UserDao;
import org.greencode.modules.app.entity.*;
import org.greencode.modules.app.service.ShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.Query;

import org.greencode.modules.app.dao.BossDao;
import org.greencode.modules.app.service.BossService;

import static org.greencode.common.constant.ClientConstants.DEFAULT_HEAD;
import static org.greencode.common.constant.ClientConstants.DEFAULT_NAME;
import static org.greencode.common.utils.DateUtils.DATE_TIME_PATTERN;


@Service("bossService")
public class BossServiceImpl extends ServiceImpl<BossDao, BossEntity> implements BossService {

    @Autowired
    private BossDao bossDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ShopService shopService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String userId = (String)params.get("userId");
        IPage<BossEntity> page = this.page(
                new Query<BossEntity>().getPage(params),
                new QueryWrapper<BossEntity>().like(StringUtils.isNotBlank(userId),"user_id", userId)
        );

        return new PageUtils(page);
    }



    @Override
    public List<BossEntity> theDay(Long userId) {

        return bossDao.selectTheDay(userId);
    }

    @Override
    public boolean getAppointment(Date dutyDate, Integer dutyType,Long shopId) {
        List<BossEntity> bossEntity = bossDao.selectByDutyDate(dutyDate, dutyType,shopId);
        if(!bossEntity.isEmpty()){
            if(bossEntity.size()<2){
                return true;
            }
            return false;
        }else {

            return true;
        }
    }

    @Override
    public List<BossEntity> findNextThreeDay() {
        return bossDao.findNextThreeDay();
    }

    //方式1：获取shopId 根据这个id获取数据，赋值
    @Override
    public PageUtils getByUserId(Map<String, Object> params) {
        Long userId = Long.parseLong(params.get("userId").toString());
        Integer pageNum=null;
        Integer pageSize=null;
        Integer type=null;
        if(params.get("pageNum")!=null){
            pageNum = Integer.parseInt(params.get("pageNum").toString());
        }
        if(params.get("pageNum")!=null){
            pageSize = Integer.parseInt(params.get("pageSize").toString());
        }
        //type=1所有，type=2未开始，type=3已完成，type=4已取消)
        if(params.get("type")!=null){
            type = Integer.parseInt(params.get("type").toString());
        }
        int start=0;
        int end  =10;
        Page<BossVO> BossVOPage = new Page<>();
        if(pageNum!=null&pageNum!=null){
            start=(pageNum-1)*pageSize;
            end=pageSize;
            BossVOPage.setSize(pageSize);
            BossVOPage.setCurrent(pageNum);
        }
        //type=1所有，type=2未开始，type=3已完成，type=4已取消)

        List<BossVO> list = bossDao.selectBossByUserId(userId,start,end,type);
        BossVOPage.setTotal(bossDao.queryPageBossVOPageCount(userId,type));
        //current 和size来自前端的参数，total是符合条件的记录数
        BossVOPage.setRecords(list);
        return new PageUtils(BossVOPage);

    }






    @Override
    public List<BossEntity> completed(Long userId) {
        //查找当天的并判断是否完成
        List<BossEntity> bossEntityList = bossDao.selectTheDay(userId);

        //查找以前已经完成的
        List<BossEntity> bossEntities = bossDao.selectCompleted(userId);
        if(bossEntityList!=null){
            BossEntity bossEntity = null;
            if(bossEntityList.size()>1){
                 bossEntity=bossEntityList.get(1);
            }else {
                bossEntity=bossEntityList.get(0);
            }
            //拿到预约上午还是下午
            Integer dutyType = bossEntity.getDutyType();
            //拿到预约当天的日期
            Date dutyDate = bossEntity.getDutyDate();
            long longDate = System.currentTimeMillis();
            Date start = new Date(longDate);
            SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);

            //System.out.println("显示输入的日期:" + dutyDate); //显示输入的日期
            Calendar cal = Calendar.getInstance();
            cal.setTime(dutyDate);
            Date time=null;
            if(dutyType==1){
                cal.add(Calendar.HOUR, 6);// 24小时制
                time = cal.getTime();

                //System.out.println("显示更新后的日期:" + format.format(dutyDate));  //显示更新后的日期
            }else if(dutyType==2){
                cal.add(Calendar.HOUR, 5);// 24小时制
                cal.add(Calendar.MINUTE, 30);// 24小时制
                time = cal.getTime();
               // System.out.println("显示更新后的日期:" + format.format(dutyDate));  //显示更新后的日期
            }
            long cha = start.getTime() - time.getTime();
            if(cha>0){
                bossEntities.add(bossEntity);
            }
        }
        return bossEntities;
    }



    @Override
    public List<HomeBossVO> findtheMonthBoss() {
        List<HomeBossVO> HomeBossVOS = bossDao.selectTheMonthBoss();
        HomeBossVOS.forEach(HomeBossVO ->{
            ShopEntity shopEntity = shopDao.selectById(HomeBossVO.getShopId());
            HomeBossVO.setShopName(shopEntity.getShopName());
            if(HomeBossVO.getFaceOpen()==0){
                HomeBossVO.setFace(DEFAULT_HEAD);
            }
        });
        return HomeBossVOS;
    }

    @Override
    public List<BossEntity> findBossByUserId(Long userId) {
        QueryWrapper<BossEntity> queryWrapper =new QueryWrapper<BossEntity>();
        queryWrapper.eq("user_id",userId);


        return bossDao.selectList(queryWrapper);
    }

    @Override
    public BossEntity newTheDay(Long userId) {
        List<BossEntity> bossEntityList = bossDao.selectTheDay(userId);
        if(bossEntityList!=null){
            BossEntity bossEntity = null;
            if(bossEntityList.size()>1){
                //如果一天两次排班那么就是整天
                for(BossEntity bossEntity1:bossEntityList){
                    if(bossEntity1.getDutyType()==2){
                        Calendar start = Calendar.getInstance();
                        start.setTime(bossEntity1.getDutyDate());
                        start.add(Calendar.HOUR, -5);// 24小时制
                        start.add(Calendar.MINUTE, -30);// 24小时制

                        Calendar end = Calendar.getInstance();
                        end.setTime(bossEntity1.getDutyDate());
                        end.add(Calendar.HOUR, 5);// 24小时制
                        end.add(Calendar.MINUTE, 30);// 24小时制

                        //现在的时间
                        Calendar date = Calendar.getInstance();
                        date.setTime(new Date());
                        if(date.after(start) && date.before(end)){
                            return bossEntity1;
                        }else {
                            return null;
                        }
                    }
                }
            }else {
                //排班是上午或者下午
                bossEntity=bossEntityList.get(0);
                //拿到预约上午还是下午
                Integer dutyType = bossEntity.getDutyType();
                //拿到预约当天的日期
                Date dutyDate = bossEntity.getDutyDate();
                //开始时间
                Calendar start = Calendar.getInstance();
                start.setTime(dutyDate);
                //当前时间
                Calendar date = Calendar.getInstance();
                date.setTime(new Date());
                //结束时间
                Calendar end = Calendar.getInstance();
                end.setTime(dutyDate);
                if(dutyType==1){
                    end.add(Calendar.HOUR, 6);// 24小时制
                }else if(dutyType==2){
                    end.add(Calendar.HOUR, 5);// 24小时制
                    end.add(Calendar.MINUTE, 30);// 24小时制
                }
                System.out.println("start--------------"+start);
                System.out.println("end----------------"+end);
                System.out.println("date---------------"+date);
                System.out.println(date.after(start));
                System.out.println(date.before(end));
                if(date.after(start) && date.before(end)){
                    return bossEntity;
                }else {
                    return null;
                }
            }
        }
        return null;

    }

}