package com.team6.leangoo.controller;

import com.team6.leangoo.model.Board;
import com.team6.leangoo.model.Project;
import com.team6.leangoo.model.User;
import com.team6.leangoo.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: lfxiui
 * Date: 2017/9/13
 * Time: 10:17
 */
@RestController
@RequestMapping("/Project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/getArchiveProject", method = RequestMethod.POST)
    public AjaxResult getArchiveProject() {
        Integer userId = 1;
        User user = new User();
        user.setUserId(userId);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            ajaxResult.setData(projectService.getArchiveProjectList(user));
            ajaxResult.seterrcode(0);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/archiveProject", method = RequestMethod.POST)
    public AjaxResult archiveProject(@RequestBody Project project) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            project.setProjectIsArchive(1);
            if (projectService.updateProject(project) == 1) ajaxResult.seterrcode(0);
            else {
                ajaxResult.seterrcode(10);
                ajaxResult.setinfo("归档失败");
            }
            return ajaxResult;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/getUserProjectList", method = RequestMethod.POST)
    public AjaxResult getUserProjectList() {
        Integer userId = 1;
        User user = new User();
        user.setUserId(userId);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            ajaxResult.setData(projectService.getUserProjectList(user));
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/newProject", method = RequestMethod.POST)
    public AjaxResult newProject(@RequestBody Project project) {
        Integer userId = 1;
        User user = new User();
        user.setUserId(userId);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            project.setProjectCreateDate(new Date());
            Integer projectId = projectService.newProject(user, project);
            if (projectId > 0) {
                ajaxResult.seterrcode(0);
                Map map = new HashMap();
                map.put("projectId", projectId);
                ajaxResult.setData(map);
            } else {
                ajaxResult.seterrcode(10);
                ajaxResult.setinfo("新增失败");
            }
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/getProjectInfo", method = RequestMethod.POST)
    public AjaxResult getProjectInfo(@RequestBody Project project) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            project = projectService.getProjectInfo(project);
            ajaxResult.seterrcode(0);
            Map map = new HashMap();
            map.put("projectId", project.getProjectId());
            map.put("projectName", project.getProjectName());
            map.put("projectIntro", project.getProjectIntro());
            ajaxResult.setData(map);
            return ajaxResult;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/getProjectLeaguerList", method = RequestMethod.POST)
    public AjaxResult getProjectLeaguerList(@RequestBody Project project) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            ajaxResult.seterrcode(0);
            ajaxResult.setData(projectService.getProjectLeaguerList(project));
            return ajaxResult;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/addProjectLeaguer", method = RequestMethod.POST)
    public AjaxResult addProjectLeaguer(@RequestBody Map map) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            User user = new User();
            user.setUserAccount(map.get("leaguer").toString());
            Project project = new Project();
            project.setProjectId(Integer.valueOf(map.get("projectId").toString()));
            if (projectService.addProjectLeaguer(project, user) == 1) {
                ajaxResult.seterrcode(0);
            } else {
                ajaxResult.seterrcode(10);
                ajaxResult.setinfo("添加失败");
            }
            ajaxResult.setData(projectService.getProjectLeaguerList(project));
            return ajaxResult;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/deleteProjectLeaguer", method = RequestMethod.POST)
    public AjaxResult deleteProjectLeaguer(@RequestBody Map map) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            User user = new User();
            user.setUserAccount(map.get("userAccount").toString());
            Project project = new Project();
            project.setProjectId(Integer.valueOf(map.get("projectId").toString()));
            if (projectService.deleteProjectLeaguer(project, user) == 1) {
                ajaxResult.seterrcode(0);
                ajaxResult.setData(projectService.getProjectLeaguerList(project));
            } else {
                ajaxResult.seterrcode(10);
                ajaxResult.setinfo("删除失败");
            }
            return ajaxResult;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/changeProjectInfo", method = RequestMethod.POST)
    public AjaxResult changeProjectInfo(@RequestBody Project project) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            if (projectService.updateProject(project) == 1) {
                ajaxResult.seterrcode(0);
            } else {
                ajaxResult.seterrcode(10);
                ajaxResult.setinfo("修改失败");
            }
            ajaxResult.setData(projectService.getProjectInfo(project));
            return ajaxResult;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/getBoardListByProjectId", method = RequestMethod.POST)
    public AjaxResult getBoardListByProjectId(@RequestBody Project project) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            ajaxResult.setData(projectService.getBoardListByProjectId(project));
            ajaxResult.seterrcode(0);
            return ajaxResult;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }
}
