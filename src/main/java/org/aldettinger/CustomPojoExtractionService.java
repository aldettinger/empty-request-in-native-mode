/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aldettinger;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped
public interface CustomPojoExtractionService {

    @RegisterForReflection
    static class CustomPojo {
        @JsonProperty(required = true)
        public boolean customerSatisfied;

        @JsonProperty(required = true)
        public String customerName;

        @JsonProperty(required = true)
        public LocalDate customerBirthday;

        @JsonProperty(required = true)
        public String summary;
    }

    static final String CUSTOM_POJO_EXTRACT_PROMPT = "Extract information about a customer from the text delimited by triple backticks: ```{text}```."
            + "The customerBirthday field should be formatted as {dateFormat}."
            + "The summary field should concisely relate the customer main ask.";

    @UserMessage(CUSTOM_POJO_EXTRACT_PROMPT)
    CustomPojo extractFromText(String text, String dateFormat);
}
