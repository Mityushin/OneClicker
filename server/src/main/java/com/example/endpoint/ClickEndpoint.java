/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.endpoint;

import com.example.service.ClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dmitry Mityushin
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/api/v1/click")
@RequiredArgsConstructor
public class ClickEndpoint {

    private final ClickService service;

    @PostMapping(path = "", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> incrementCounter() {
        return ResponseEntity.ok(service.increment());
    }

    @GetMapping(path = "", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getCounter() {
        return ResponseEntity.ok(service.getCounter());
    }
}
