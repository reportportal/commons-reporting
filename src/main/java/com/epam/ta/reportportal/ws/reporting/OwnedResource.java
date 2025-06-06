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

import static com.epam.ta.reportportal.ws.reporting.ValidationConstraints.MAX_ENTITY_DESCRIPTION;
import static com.epam.ta.reportportal.ws.reporting.ValidationConstraints.MIN_DESCRIPTION;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Base entity for sharable resources. This resource should contains owner in response.
 *
 * @author Aliaksei_Makayed
 */
@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class OwnedResource {

  @JsonProperty(value = "owner")
  private String owner;

  @Size(min = MIN_DESCRIPTION, max = MAX_ENTITY_DESCRIPTION)
  private String description;

  @Override
  public String toString() {
    return "OwnedResource{" + "owner='" + owner + '\''
        + ", description='" + description + '\''
        + '}';
  }
}
