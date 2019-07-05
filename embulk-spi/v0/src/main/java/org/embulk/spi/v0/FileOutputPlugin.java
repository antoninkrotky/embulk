/*
 * Copyright 2019 The Embulk project
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
 */

package org.embulk.spi.v0;

import java.util.List;
import org.embulk.api.v0.ConfigDiff;
import org.embulk.api.v0.ConfigSource;
import org.embulk.api.v0.TaskReport;
import org.embulk.api.v0.TaskSource;

public interface FileOutputPlugin {
    interface Control {
        List<TaskReport> run(TaskSource taskSource);
    }

    ConfigDiff transaction(ConfigSource config, int taskCount, FileOutputPlugin.Control control);

    ConfigDiff resume(TaskSource taskSource, int taskCount, FileOutputPlugin.Control control);

    void cleanup(TaskSource taskSource, int taskCount, List<TaskReport> successTaskReports);

    TransactionalFileOutput open(TaskSource taskSource, int taskIndex);
}
