/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.indices.analysis.ik;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.TokenStream;

import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKTokenizer;

import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.analysis.*;
import org.elasticsearch.indices.analysis.IndicesAnalysisService;

import org.elasticsearch.env.Environment;

import java.io.Reader;

/**
 * Registers indices level analysis components so, if not explicitly configured, will be shared
 * among all indices.
 */
public class IKIndicesAnalysis extends AbstractComponent {

    @Inject
    public IKIndicesAnalysis(Settings settings, IndicesAnalysisService indicesAnalysisService) {
        super(settings);

        protected Enviroment env;

        this.env = new Enviroment(settings);

        Dictionary.initial(new Configuration(env));

        // Register ik analyzer
        indicesAnalysisService.analyzerProviderFactories().put("ik", new PreBuiltAnalyzerProviderFactory("smartcn", AnalyzerScope.INDICES, new IKAnalyzer(null, settings, env));

        // Register ik tokenizer
        indicesAnalysisService.tokenizerFactories().put("ik", new PreBuiltTokenizerFactoryFactory(new TokenizerFactory() {
            @Override
            public String name() {
                return "ik";
            }

            @Override
            public Tokenizer create(Reader reader) {
                return new IKTokenizer(reader, settings, env);
            }
        }));

    }
}
