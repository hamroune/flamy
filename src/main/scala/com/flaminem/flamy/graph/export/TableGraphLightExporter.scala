/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.flaminem.flamy.graph.export

import com.flaminem.flamy.graph.TableGraph
import com.flaminem.flamy.model.names.TableName

/**
 * Created by fpin on 1/9/15.
 */
class TableGraphLightExporter(preTableGraph: TableGraph) extends TableGraphExporter(preTableGraph) {
  override def formatTableVertex(td: TableName): Seq[Seq[Seq[AlignedString]]] = {
    val nodeName = td.name
    Seq(Seq(Seq(AlignedString(nodeName))))
  }
}