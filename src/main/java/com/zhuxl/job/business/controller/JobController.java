package com.zhuxl.job.business.controller;

import com.github.pagehelper.PageInfo;
import com.mfx.micro.service.timetask.controller.base.BaseController;
import com.zhuxl.job.business.entity.ScheduleJobDO;
import com.zhuxl.job.business.service.JobService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author cnlm.me@qq.com
 * @date 2017/7/22
 */
@RestController
@RequestMapping("api/v1")
public class JobController extends BaseController {

    @Autowired
    private JobService jobService;

    @ApiOperation(value = "任务列表", notes = "获取数据库所有任务列表")
    @GetMapping("job/list.ajax")
    @ResponseBody
    public ResponseEntity jobList(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "pageNum") int pageNum,
                                  @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<ScheduleJobDO> pageInfo = jobService.getTasksForPage(pageNum, pageSize);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "添加任务", notes = "添加一个新的任务")
    @PostMapping("job/add_job.ajax")
    @ResponseBody
    public ResponseEntity addJob(@RequestBody ScheduleJobDO scheduleJob) {
        jobService.addTask(scheduleJob);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @ApiOperation(value = "修改任务", notes = "修改一个存在的任务")
    @PutMapping("job/update_job.ajax")
    @ResponseBody
    public ResponseEntity modifyJob(@RequestBody ScheduleJobDO scheduleJob) {
        jobService.editTask(scheduleJob);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @ApiOperation(value = "删除任务", notes = "删除一个存在的任务")
    @DeleteMapping("job/delete_job.ajax")
    @ResponseBody
    public ResponseEntity deleteJob(@RequestParam("jobId") Long jobId) {
        jobService.delTaskById(jobId);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @ApiOperation(value = "切换任务状态", notes = "改变任务运行状态(停止、运行)")
    @PutMapping("job/switch_run_status.ajax")
    @ResponseBody
    public ResponseEntity changeJobStatus(@RequestParam("jobId") Long jobId, @RequestParam("cmd") String cmd) {
        if (!"start".equals(cmd) && !"stop".equals(cmd)) {
            return new ResponseEntity("任务状态改变失败！", HttpStatus.OK);
        }
        jobService.changeStatus(jobId, cmd);

        return new ResponseEntity(null, HttpStatus.OK);
    }
}
