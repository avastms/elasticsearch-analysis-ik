package org.elasticsearch.plugin.analysis.ik;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.IkAnalysisBinderProcessor;
import org.elasticsearch.plugins.AbstractPlugin;


import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;


public class AnalysisIkPlugin extends AbstractPlugin {

    public static ESLogger logger=Loggers.getLogger("ik-analyzer");

    @Override public String name() {
        return "analysis-ik";
    }


    @Override public String description() {
        return "ik analysis";
    }


    @Override public void processModule(Module module) {
        logger.info('really in processModule');
        if (module instanceof AnalysisModule) {
            logger.info('really in addProcessor');
            AnalysisModule analysisModule = (AnalysisModule) module;
            analysisModule.addProcessor(new IkAnalysisBinderProcessor());
        }
    }
    
    public void onModule(AnalysisModule module) {
        logger.info('really in onModule');
        module.addProcessor(new IkAnalysisBinderProcessor());
    }
}
