package com.epam.ta.reportportal.ws.reporting.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeInstantSerializer extends JsonSerializer<Instant> {

  private static final DateTimeFormatter formatter = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
      .withZone(ZoneOffset.UTC);

  @Override
  public void serialize(Instant value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeString(formatter.format(value));
  }
}
