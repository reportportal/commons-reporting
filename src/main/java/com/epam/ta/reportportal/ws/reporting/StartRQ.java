/*
 * Copyright 2019 EPAM Systems
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

package com.epam.ta.reportportal.ws.reporting;

import static com.epam.ta.reportportal.ws.reporting.ValidationConstraints.MAX_PARAMETERS_LENGTH;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.util.Date;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Base entity for start requests
 *
 * @author Andrei Varabyeu
 */
@JsonInclude(Include.NON_NULL)
public class StartRQ {

  @JsonProperty(value = "name", required = true)
  @Schema(requiredMode = RequiredMode.REQUIRED)
  protected String name;

  @JsonProperty(value = "description")
  private String description;

  @Size(max = MAX_PARAMETERS_LENGTH)
  @Valid
  @JsonProperty("attributes")
  @JsonAlias({"attributes", "tags"})
  private Set<ItemAttributesRQ> attributes;

  @NotNull
  @JsonProperty(required = true)
  @JsonAlias({"startTime", "start_time"})
  @Schema(requiredMode = RequiredMode.REQUIRED)
  private Date startTime;

  @Schema(requiredMode = RequiredMode.REQUIRED)
  @JsonProperty(value = "uuid")
  private String uuid;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<ItemAttributesRQ> getAttributes() {
    return attributes;
  }

  public void setAttributes(Set<ItemAttributesRQ> attributes) {
    this.attributes = attributes;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  @Override
  public String toString() {
    return "StartRQ{" + "name='" + name + '\''
        + ", description='" + description + '\''
        + ", attributes=" + attributes
        + ", startTime=" + startTime
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    StartRQ startRQ = (StartRQ) o;

    if (name != null ? !name.equals(startRQ.name) : startRQ.name != null) {
      return false;
    }
    if (description != null ? !description.equals(startRQ.description)
        : startRQ.description != null) {
      return false;
    }
    if (attributes != null ? !attributes.equals(startRQ.attributes) : startRQ.attributes != null) {
      return false;
    }
    return startTime != null ? startTime.equals(startRQ.startTime) : startRQ.startTime == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
    result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
    return result;
  }
}