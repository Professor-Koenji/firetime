package com.koenji.firetime.builder;

import com.koenji.ecs.component.IComponent;

public interface IComponentFactory {

  IComponent getComponent(String name, float ...options);
}
