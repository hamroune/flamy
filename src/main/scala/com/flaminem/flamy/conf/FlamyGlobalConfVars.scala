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

package com.flaminem.flamy.conf

import com.flaminem.flamy.conf.ConfLevel.Global
import com.flaminem.flamy.exec.utils.io.FlamyOutput
import com.flaminem.flamy.utils.macros.SealedValues
import com.typesafe.config.Config
import org.apache.commons.configuration.Configuration
import org.apache.hadoop.fs.Path

import scala.reflect.runtime.universe._

/**
  * These configuration parameters are global to the whole JVM.
  * They can be changed without restarting the JVM, but having two threads with different values
  * of these properties is not possible.
  */
private[conf]
class FlamyGlobalConfVars { self =>
  var conf: Config = _

  object RUN_DIR extends
    GlobalConfVar[String](
      varName = "run.dir.path",
      defaultValue = Some(new Path(s"/tmp/${Flamy.name}-${SystemContext.userName}").toString),
      validator = Validator.Required(),
      description = "Set the directory in which all the temporary outputs will be written. " +
        s"By default this is a temporary directory created in /tmp/${Flamy.name}-$$USER."
    )

  object RUN_DIR_CLEANING_DELAY extends
    GlobalConfVar[Int](
      varName = "run.dir.cleaning.delay",
      defaultValue = Some(24),
      validator = Validator.Required(),
      description = "Set the number of hours for which all the run directories older than this time laps " +
        s"will be automatically removed. Automatic removal occurs during each ${Flamy.name} command startup."
    )

  object REGEN_STATIC_SYMBOL extends
    GlobalConfVar[String](
      varName = "regen.static.symbol",
      defaultValue = Some("\u2713"),
      validator = Validator.Required(),
      description = "Set the symbol used to represent partitions that the regen can predict.",
      hidden = true
    )

  object REGEN_DYNAMIC_SYMBOL extends
    GlobalConfVar[String](
      varName = "regen.dynamic.symbol",
      defaultValue = Some("\u2715"),
      validator = Validator.Required(),
      description = "Set the symbol used to represent partitions that the regen cannot predict and will handle dynamically.",
      hidden = true
    )

  object REGEN_SHOW_INPUTS extends
    GlobalConfVar[Boolean](
      varName = "regen.show.inputs",
      defaultValue = Some(true),
      validator = Validator.Required(),
      description = "(experimental feature) This this to true display the number of input partition when running a regen.",
      hidden = true
    )

  object USE_OLD_REGEN extends
    GlobalConfVar[Boolean](
      varName = "regen.use.legacy",
      defaultValue = Some(false),
      validator = Validator.Required(),
      description = "Use the old version of the regen."
    )

  object DYNAMIC_OUTPUT extends
    GlobalConfVar[Boolean](
      varName = "io.dynamic.output",
      defaultValue = Some(true),
      validator = Validator.Required(),
      description = "The run and regen commands will use a dynamic output, instead of a static output. " +
        "Only work with terminals supporting ANSI escape codes."
    )

  object USE_HYPERLINKS extends
    GlobalConfVar[Boolean](
      varName = "io.use.hyperlinks",
      defaultValue = Some(true),
      validator = Validator.Required(),
      description = s"Every file path that ${Flamy.name} prints will be formatted as a url. " +
        s"In some shells, this allows CTRL+clicking the link to open the file."
    )

  object BROWSER_GUI extends
    GlobalConfVar[Boolean](
      varName = "io.browser",
      defaultValue = Some(false),
      validator = Validator.Required(),
      description = "When this is set to true, flamy's output will be altered to be made exploitable by a javascript front-end."
    )

  object AUTO_OPEN_COMMAND extends
    GlobalConfVar[String](
      varName = "auto.open.command",
      defaultValue = Some(SystemContext.osFamily.openCommand),
      validator = Validator.Required(),
      description = "Some commands like 'show graph' generate a file and  automatically open it. " +
        "Use this option to specify the command to use when opening the file," +
        "or set it to an empty string to disable the automatic opening of the files."
    )

  object AUTO_OPEN_MULTI extends
    GlobalConfVar[Boolean](
      varName = "auto.open.multi",
      defaultValue = Some(SystemContext.osFamily.isMultiOpenSuported),
      validator = Validator.Required(),
      description = "In addition with auto.open.command, this boolean flag indicates if multiple files should be open simultaneously."
    )

  object VERBOSITY_LEVEL extends
    GlobalConfVar[String](
      varName = "verbosity.level",
      defaultValue = Some("INFO"),
      validator = Validator.In(FlamyOutput.LogLevel.logLevelNames),
      description = "Controls the verbosity level of flamy."
    )

  sealed class GlobalConfVar[T] (
    override val varName: String,
    override val defaultValue: Option[T],
    override val validator: Validator[T],
    override val description: String,
    override val hidden: Boolean = false
  )(implicit override val typeTag: TypeTag[T]) extends ConfVarTemplate[T] {

    override def confLevel = ConfLevel.Global

    override def conf: Config = self.conf

    override def propertyKey: String = s"${Flamy.name}.$varName"

  }

  /* This line must stay after the value declaration or it will be empty */
  val confVars: Seq[GlobalConfVar[_]] = SealedValues.values[GlobalConfVar[_]]

}
