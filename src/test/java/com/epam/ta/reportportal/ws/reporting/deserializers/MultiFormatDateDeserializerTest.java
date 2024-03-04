package com.epam.ta.reportportal.ws.reporting.deserializers;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class MultiFormatDateDeserializerTest {

  private static final Instant expectedTime = Instant.parse("2024-03-01T20:24:09.930Z");

  @Mock
  JsonParser jsonParser;

  @ParameterizedTest
  @ValueSource(strings = {
      "2024-03-01T20:24:09.930987Z",
      "2024-03-01T20:24:09.930987654Z",
      "2024-03-01T20:24:09.930Z",
      "2024-03-01T20:24:09.930",
      "2024-03-01T20:24:09.930+00:00",
  })
  void deserializeDates(String strDate) throws IOException {
    MultiFormatDateDeserializer a = new MultiFormatDateDeserializer();
    when(jsonParser.getText()).thenReturn(strDate);
    Instant date = a.deserialize(jsonParser, mock(DeserializationContext.class));

    Assertions.assertEquals(expectedTime, date.truncatedTo(ChronoUnit.MILLIS));
  }

  @ParameterizedTest
  @ValueSource(longs = {
      1709324649930L,
      //    1709324649930654L,
      // 1709324649930654321L

  })
  void deserializeIntegerFormat(Long longDate) throws IOException {
    MultiFormatDateDeserializer a = new MultiFormatDateDeserializer();
    when(jsonParser.getLongValue()).thenReturn(longDate);
    Instant date = a.deserialize(jsonParser, mock(DeserializationContext.class));

    Assertions.assertEquals(expectedTime, date.truncatedTo(ChronoUnit.MILLIS));
  }
}
