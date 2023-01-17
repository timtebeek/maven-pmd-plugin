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

File buildLog = new File( basedir, 'build.log' )
assert buildLog.exists()
assert buildLog.text.contains( "PMD processing errors" )
assert buildLog.text.contains( "ParseException" )

String disabledPath = new File( basedir, 'logging-disabled/src/main/java/BrokenFile.java' ).getCanonicalPath()
String enabledPath = new File( basedir, 'logging-enabled/src/main/java/BrokenFile.java' ).getCanonicalPath()

// logging disabled: the pmd exception is only output through the processing error reporting (since MPMD-246)
assert 1 == buildLog.text.count( "${disabledPath}: ParseException: Encountered" )
// logging enabled: the pmd exception is output twice: through the processing error reporting (since MPMD-246) and through PMD's own logging
assert 2 == buildLog.text.count( "${enabledPath}: ParseException: Encountered" )
