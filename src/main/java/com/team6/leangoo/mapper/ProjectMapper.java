package com.team6.leangoo.mapper;

import com.team6.leangoo.model.Project;
import com.team6.leangoo.util.MyMapper;

import java.util.List;

public interface ProjectMapper extends MyMapper<Project> {
     List getArchiveProjects(int userId);
     List<Project> getUserProjectList(int userId);
     List getProjectLeaguerList(Integer projectId);
     List getBoardListByProjectId(Integer projectId);
}