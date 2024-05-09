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

package org.apache.comet.shims

import org.apache.spark.sql.connector.expressions.aggregate.Aggregation
import org.apache.spark.sql.execution.{CollectLimitExec, GlobalLimitExec, LocalLimitExec, QueryExecution}
import org.apache.spark.sql.execution.datasources.v2.parquet.ParquetScan

trait ShimCometSparkSessionExtensions {
  protected def getPushedAggregate(scan: ParquetScan): Option[Aggregation] = scan.pushedAggregate

  protected def getOffset(limit: LocalLimitExec): Int = 0
  protected def getOffset(limit: GlobalLimitExec): Int = limit.offset
  protected def getOffset(limit: CollectLimitExec): Int = limit.offset

  protected def supportsExtendedExplainInfo(qe: QueryExecution): Boolean = true
}
