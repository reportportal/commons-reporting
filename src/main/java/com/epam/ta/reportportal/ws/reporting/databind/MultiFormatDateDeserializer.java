/*
 * Copyright 2024 EPAM Systems
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

package com.epam.ta.reportportal.ws.reporting.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deserialization class for parsing incoming dates of different formats.
 *
 * @author Siarhei Hrabko
 */
public class MultiFormatDateDeserializer extends JsonDeserializer<Instant> {

  private static final DateTimeFormatter TIMESTAMP_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"));

  private static final DateTimeFormatter LOCAL_DATE_TIME_MS_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").withZone(ZoneId.of("UTC"));

  @Override
  public Instant deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    try {
      long longDate = parser.getLongValue();
      if (longDate > 0) {
        return Instant.ofEpochMilli(longDate);
      }
    } catch (Exception e) {
      // do nothing
    }

    String strDate = parser.getText();
    try {
      return Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(strDate));
    } catch (Exception e) {
      // do nothing
    }
    try {
      return Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(strDate));
    } catch (DateTimeParseException e) {
      // do nothing
    }

    try {
      return Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(strDate));
    } catch (DateTimeParseException e) {
      // do nothing
    }

    try {
      return Instant.from(LOCAL_DATE_TIME_MS_FORMAT.parse(strDate));
    } catch (DateTimeParseException e) {
      // do nothing
    }

    // without try catch block for the last one attempt
    return Instant.from(TIMESTAMP_FORMAT.parse(strDate));

  }
}
