package com.tositteach.service.impl;

import com.tositteach.domain.mapper.ClazzMapper;
import com.tositteach.domain.entity.Clazz;
import com.tositteach.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService{
    @Autowired
    ClazzMapper clazzMapper;

    //查询clazz表中所有信息
    @Override
    public List<Clazz> queryAllClazz() {
        return clazzMapper.selectAllClazz();
    }

    //根据班级id查询相关班级信息
    @Override
    public Clazz queryClazzById(String clazId) {
        return clazzMapper.selectClazzById(clazId);
    }

    //根据选中班级的cla_id删除班级信息
    @Override
    public Integer removeClazz(String clazId) {
        return clazzMapper.deleteClazz(clazId);
    }

    //增加新的班级及其信息，返回添加条数
    @Override
    public Integer addClazz(Clazz clazz) {
        return clazzMapper.insertClazz(clazz);
    }

    @Override
    public void getRightId(Clazz clazz) {

            List<Clazz> clazzes = clazzMapper.selectAllClazz();
            if(clazzes.size()==0){
                clazz.setClaId("1");
            }
            else{
                ArrayList<Integer> iid = new ArrayList<Integer>();
                //最后一个元素
                for (Clazz clazz1 : clazzes){
                    iid.add(new Integer(clazz1.getClaId()));
                }
                //排序从小到大
                Collections.sort(iid);
                int id1 = iid.get(iid.size()-1)+1;
                System.out.println(id1+"\n\n\n\n\n\n");
                String id = String.valueOf(id1);
                clazz.setClaId(id);
            }

    }


}
