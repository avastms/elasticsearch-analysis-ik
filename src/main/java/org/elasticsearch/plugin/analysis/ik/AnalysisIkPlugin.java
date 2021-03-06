package org.elasticsearch.plugin.analysis.ik;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.collect.ImmutableList;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.IkAnalysisBinderProcessor;
import org.elasticsearch.plugins.AbstractPlugin;

import org.elasticsearch.indices.analysis.ik.IKIndicesAnalysisModule;

import java.util.Collection;

public class AnalysisIkPlugin extends AbstractPlugin {

    @Override public String name() {
        return "analysis-ik";
    }


    @Override public String description() {
        return "ik analysis";
    }

    @Override
    public Collection<Class<? extends Module>> modules() {
        return ImmutableList.<Class<? extends Module>>of(IKIndicesAnalysisModule.class);
    }
    //@Override public void processModule(Module module) {
    //    logger.info("really in processModule");
    //    if (module instanceof AnalysisModule) {
    //        logger.info("really in addProcessor");
    //        AnalysisModule analysisModule = (AnalysisModule) module;
    //        analysisModule.addProcessor(new IkAnalysisBinderProcessor());
    //    }
    //}
    
    public void onModule(AnalysisModule module) {
        module.addProcessor(new IkAnalysisBinderProcessor());
    }
}
