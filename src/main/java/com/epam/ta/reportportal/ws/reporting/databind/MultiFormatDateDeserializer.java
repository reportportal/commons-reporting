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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

  private static final DateTimeFormatter LOCAL_DATE_TIME_MS_FORMAT_DATE =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXX").withZone(ZoneId.of("UTC"));

  @Override
  public Instant deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    try {
      long longDate = parser.getLongValue();
      if (parser.getText() == null) {
        return Instant.ofEpochMilli(longDate);
      }
      // ignore
    } catch (Exception e) {
      // ignore
    }
    try {
      long millis = Long.parseLong(parser.getText());
      return Instant.ofEpochMilli(millis);
    } catch (Exception e) {
      // ignore
    }

    String strDate = parser.getText();

    List<DateTimeFormatter> formatters = new ArrayList<>();
    formatters.add(DateTimeFormatter.RFC_1123_DATE_TIME);
    formatters.add(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    formatters.add(DateTimeFormatter.ISO_DATE_TIME);
    formatters.add(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    formatters.add(TIMESTAMP_FORMAT);
    formatters.add(LOCAL_DATE_TIME_MS_FORMAT);
    formatters.add(LOCAL_DATE_TIME_MS_FORMAT_DATE);

    for (DateTimeFormatter formatter : formatters) {
      try {
        return ZonedDateTime.from(formatter.parse(strDate)).toInstant();
      } catch (Exception e) {
        try {
          return LocalDateTime.from(formatter.parse(strDate)).toInstant(ZoneOffset.UTC);
        } catch (Exception ex) {
        }
      }
    }
    throw new RuntimeException();
  }
}
