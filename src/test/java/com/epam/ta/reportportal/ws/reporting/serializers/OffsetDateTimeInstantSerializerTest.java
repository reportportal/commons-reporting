package com.epam.ta.reportportal.ws.reporting.serializers;

import com.epam.ta.reportportal.ws.reporting.LaunchResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class OffsetDateTimeInstantSerializerTest {

  private static final String EXPECTED_PARSED_TIME = "2024-11-01T08:39:14.125000+0000";

  @Test
  @Disabled
  void serializeTimeInCompatibleFormat() throws IOException {
    LaunchResource launchResource = new LaunchResource();
    launchResource.setStartTime(Instant.ofEpochMilli(1730450354125L));
    ObjectMapper objectMapper = new ObjectMapper()
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .registerModule(new JavaTimeModule());
    String s = objectMapper.writeValueAsString(launchResource);
    Assertions.assertTrue(s.contains(EXPECTED_PARSED_TIME));
  }

}
