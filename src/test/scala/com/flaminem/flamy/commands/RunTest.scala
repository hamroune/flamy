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

package com.flaminem.flamy.commands

import com.flaminem.flamy.Launcher
import com.flaminem.flamy.exec.utils.ReturnFailure
import org.scalatest.{FreeSpec, Matchers}

class RunTest extends FreeSpec with Matchers {

  "When running or a table that does not exists, an error should be thrown" in {
    val exitCode = Launcher.launch("--conf flamy.model.dir.paths=src/test/resources/test run --dry db_dest.dest missing.table".split(" "))
    exitCode should be (ReturnFailure)
  }

}
