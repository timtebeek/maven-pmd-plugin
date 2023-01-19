package org.apache.maven.plugins.pmd.exec;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.IOException;
import java.io.Writer;

import net.sourceforge.pmd.cpd.CPDReport;
import net.sourceforge.pmd.cpd.renderer.CPDRenderer;
import net.sourceforge.pmd.cpd.renderer.CPDReportRenderer;

final class CPDReportRendererAdapter implements CPDReportRenderer
{
    private final CPDRenderer renderer;

    CPDReportRendererAdapter( CPDRenderer renderer )
    {
        this.renderer = renderer;
    }

    @Override
    public void render( CPDReport cpdReport, Writer writer ) throws IOException
    {
        renderer.render( cpdReport.getMatches().iterator(), writer );
    }
}
