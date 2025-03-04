package com.batching.app.mapper;

import com.batching.app.entity.ProjectEntity;
import com.batching.app.model.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper extends BaseMapper<ProjectEntity, ProjectDto> {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
}