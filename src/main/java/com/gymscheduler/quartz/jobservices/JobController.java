package com.gymscheduler.quartz.jobservices;

import com.gymscheduler.quartz.info.TimerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gymscheduler/job")
public class JobController {

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/runHelloWorld")
    public void runHelloWorld() {
        jobService.runHelloWorldJob();
    }

    @GetMapping
    public List<TimerInfo> getAllRunningTimers() {
        return jobService.getAllRunningTimers();
    }

    @GetMapping("/{timerId}")
    public TimerInfo getRunningTimer(@PathVariable String timerId){
        return jobService.getRunningTimer(timerId);
    }

    @DeleteMapping("/{timerId}")
    public Boolean deleteTimer(@PathVariable String timerId) {
        return jobService.deleteTimer(timerId);
    }
}
