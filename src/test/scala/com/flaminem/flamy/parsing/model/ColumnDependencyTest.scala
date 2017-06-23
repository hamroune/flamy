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

package com.flaminem.flamy.parsing.model

import org.scalatest.FreeSpec

/**
  * Created by fpin on 12/10/16.
  */
class ColumnDependencyTest extends FreeSpec {

  "matches should work" in {
    assert (new ColumnDependency("c").matches(new ColumnDependency("c","t","s")))
    assert (new ColumnDependency("c").matches(new ColumnDependency("c","t")))
    assert (new ColumnDependency("c").matches(new ColumnDependency("c","t","s")))
    assert (new ColumnDependency("c","t").matches(new ColumnDependency("c","t")))
    assert (new ColumnDependency("c","t").matches(new ColumnDependency("c","t","s")))
    assert (new ColumnDependency("c","t","s").matches(new ColumnDependency("c","t","s")))

    assert (! new ColumnDependency("c").matches(new ColumnDependency("!","t","s")))
    assert (! new ColumnDependency("c").matches(new ColumnDependency("!","t")))
    assert (! new ColumnDependency("c").matches(new ColumnDependency("!","t","s")))
    assert (! new ColumnDependency("c","t").matches(new ColumnDependency("c")))
    assert (! new ColumnDependency("c","t").matches(new ColumnDependency("!","t")))
    assert (! new ColumnDependency("c","t").matches(new ColumnDependency("c","!")))
    assert (! new ColumnDependency("c","t").matches(new ColumnDependency("!","t","s")))
    assert (! new ColumnDependency("c","t").matches(new ColumnDependency("c","!","s")))
    assert (! new ColumnDependency("c","t","s").matches(new ColumnDependency("c")))
    assert (! new ColumnDependency("c","t","s").matches(new ColumnDependency("c","t")))
    assert (! new ColumnDependency("c","t","s").matches(new ColumnDependency("!","t","s")))
    assert (! new ColumnDependency("c","t","s").matches(new ColumnDependency("c","!","s")))
    assert (! new ColumnDependency("c","t","s").matches(new ColumnDependency("c","t","!")))
  }

}
