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

package com.flaminem.flamy.model.exceptions

/**
 * Created by fpin on 2/5/15.
 */
class UnexpectedBehaviorException(message: Option[String] = None, cause: Option[Throwable] = None)
  extends FlamyException(Some("This should not happen, please report a bug" + message.map{" : " + _}.getOrElse("")), cause) {

  def this(message: String) = {
    this(Option(message), None)
  }

  def this(message: String, cause: Throwable) = {
    this(Option(message), Option(cause))
  }

}
