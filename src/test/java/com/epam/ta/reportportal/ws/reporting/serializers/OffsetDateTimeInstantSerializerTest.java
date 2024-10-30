package com.epam.ta.reportportal.ws.reporting.serializers;

import com.epam.ta.reportportal.ws.reporting.LaunchResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OffsetDateTimeInstantSerializerTest {

  private static final Instant INSTANT_TIME = Instant.parse("2024-03-01T20:24:09.930930+00:00");
  private static final String EXPECTED_PARSED_TIME = "2024-03-01T20:24:09.930930+0000";

  @Test
  void serializeTimeInCompatibleFormat() throws IOException {
    LaunchResource launchResource = new LaunchResource();
    launchResource.setStartTime(INSTANT_TIME);
    ObjectMapper objectMapper = new ObjectMapper();
    String s = objectMapper.writeValueAsString(launchResource);
    Assertions.assertTrue(s.contains(EXPECTED_PARSED_TIME));
  }

}
