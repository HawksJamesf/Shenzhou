package com.hawksjamesf.plugin

import groovy.text.SimpleTemplateEngine
import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionPlugin implements Plugin<Project> {
    void apply(Project project) {

        def versionPlugin = project.extensions.create("versionPlugin", VersionPluginExtension)

        //rename app name
        //$appName-v_$versionName-c_$versionCode
        project.afterEvaluate {
            def template = versionPlugin.fileFormat
            def engine = new SimpleTemplateEngine()

            project.android.applicationVariants.matching({
                it.buildType.name.matches(versionPlugin.buildTypeMatcher)
            }).all { variant ->

                def map = [
                        'appName'    : project.name,
                        'project'    : project.rootProject.name,
                        'buildType'  : variant.buildType.name,
                        'versionName': variant.versionName,
                        'versionCode': variant.versionCode,

                ]
                def fileName = engine.createTemplate(template).make(map).toString()

                variant.outputs.all {
                    outputFileName = fileName + '.apk'


                }

            }


        }
    }
}