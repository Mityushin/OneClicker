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

package com.example.service;

import com.example.repository.ClickHolderRepository;
import com.example.repository.entity.ClickHolder;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @author Dmitry Mityushin
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class SimplePersistenceClickService implements ClickService {
    private static final Long COUNTER_ID = 1L;
    private static final int RADIX = 10;

    private final ClickHolderRepository repository;

    @Transactional
    @NotNull
    @Override
    public String getCounter() {
        return findOrCreate().getCounter();
    }

    @Transactional
    @NotNull
    @Override
    public String increment() {
        ClickHolder holder = findOrCreate();
        String raised = new BigInteger(holder.getCounter(), RADIX)
                .add(BigInteger.ONE)
                .toString(RADIX);
        holder.setCounter(raised);
        return repository.save(holder).getCounter();
    }

    @NotNull
    private ClickHolder findOrCreate() {
        return repository.findById(COUNTER_ID)
                .orElseGet(this::createCounter);
    }

    @NotNull
    private ClickHolder createCounter() {
        ClickHolder holder = new ClickHolder();
        holder.setId(COUNTER_ID);
        holder.setCounter(String.valueOf(0L));
        return repository.save(holder);
    }
}
