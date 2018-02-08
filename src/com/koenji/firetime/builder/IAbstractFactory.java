package com.koenji.firetime.builder;

import com.koenji.ecs.entity.IEntity;

public interface IAbstractFactory {

    IEntity getEntity(String entity);
}
