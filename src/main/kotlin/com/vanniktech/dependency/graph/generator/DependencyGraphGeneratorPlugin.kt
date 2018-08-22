package com.vanniktech.dependency.graph.generator

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

// TODO: figure out proper naming!
open class DependencyGraphGeneratorPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    val extension = project.extensions.create("dependencyGraphGenerator", DependencyGraphGeneratorExtension::class.java)

    project.afterEvaluate { _ ->
      extension.generators.forEach {
        val task = project.tasks.create(it.gradleTaskName, DependencyGraphGeneratorTask::class.java)
        task.generator = it
        task.group = "reporting"
        task.description = "Generates a dependency graph${it.name.nonEmptyPrepend(" for ")}"
        task.inputFile = project.buildFile
        task.outputDirectory = File(project.buildDir, "reports/dependency-graph/")
      }

      extension.projectGenerators.forEach {
        val task = project.tasks.create(it.gradleTaskName, DependencyGraphProjectGeneratorTask::class.java)
        task.projectGenerator = it
        task.group = "reporting"
        task.description = "Generates a project dependency graph${it.name.nonEmptyPrepend(" for ")}"
        task.inputFile = project.buildFile
        task.outputDirectory = File(project.buildDir, "reports/project-dependency-graph/")
      }
    }
  }
}
