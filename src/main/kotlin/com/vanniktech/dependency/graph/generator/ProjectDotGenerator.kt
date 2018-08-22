package com.vanniktech.dependency.graph.generator

import guru.nidi.graphviz.model.Factory.graph
import guru.nidi.graphviz.model.Graph
import org.gradle.api.Project

internal class ProjectDotGenerator(
  private val project: Project,
  private val generator: DependencyGraphGeneratorExtension.ProjectGenerator
) {
  fun generateGraph(): Graph {
    return graph()
  }
}
