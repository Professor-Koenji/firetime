package com.koenji.firetime.builder;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.entity.IEntity;

public interface IAbstractFactory {

    IEntity orderEntity(String entity);
    //IComponent getComponent(String component, float ...options);
}
