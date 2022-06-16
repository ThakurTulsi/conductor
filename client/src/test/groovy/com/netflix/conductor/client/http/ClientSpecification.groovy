/*
 * Copyright 2022 Netflix, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.netflix.conductor.client.http

import java.nio.charset.StandardCharsets

import org.apache.commons.io.IOUtils

import com.netflix.conductor.common.config.ObjectMapperProvider

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

abstract class ClientSpecification extends Specification {

    protected static final String ROOT_URL = "dummyroot/"

    protected static URI createURI(String path) {
        URI.create(ROOT_URL + path)
    }

    protected RequestHandler requestHandler
    protected ObjectMapper objectMapper

    def setup() {
        requestHandler = Mock(RequestHandler.class)

        objectMapper = new ObjectMapperProvider().getObjectMapper()
    }

    protected InputStream toInputStream(Object value) {
        return IOUtils.toInputStream(objectMapper.writeValueAsString(value), StandardCharsets.UTF_8)
    }

    protected static InputStream toInputStream(String value) {
        return IOUtils.toInputStream(value, StandardCharsets.UTF_8)
    }
}
