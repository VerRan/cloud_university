package com.chinasoft.offer.controller;

import com.chinasoft.offer.common.ServerResponse;
import com.chinasoft.offer.data.entity.Teacher;
import com.chinasoft.offer.data.model.TeacherQo;
import com.chinasoft.offer.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Honkey on 2017/11/16 11:48.
 */
@RestController
@CrossOrigin
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping({"/add","/update"})
    public ServerResponse<Teacher> addOrUpdate(@RequestBody Teacher teacher){
        return teacherService.addOrUpdate(teacher);
    }

    @PostMapping("/findAllOrLike")
    public ServerResponse<Page<Teacher>> findAllOrLike(@RequestBody TeacherQo teacherQo){
        return teacherService.findAllOrLike(teacherQo);
    }

    @PostMapping("/delete")
    public ServerResponse<Map<String,Integer>> delete(@RequestBody TeacherQo teacherQo){
        return  teacherService.delete(teacherQo.getId());
    }
}

