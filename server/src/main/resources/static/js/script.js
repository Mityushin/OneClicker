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

document.addEventListener("DOMContentLoaded", async _ => {
    const counter = document.getElementById('click-counter');
    try {
        counter.value = '#loading'
        const response = await fetch('/api/v1/click', {
          method: 'get'
        });
        if (response.ok) {
            const actualCount = await response.text();
            console.log(`getCounter() completed, actualCount=${actualCount}`);
            counter.value = actualCount;
        } else {
            const statusCode = response.status;
            console.log(`getCounter() failed, statusCode=${statusCode}`)
            counter.value = '#error, statusCode=' + statusCode;
        }
    } catch(err) {
        console.error('getCounter() failed', err);
        counter.value = '#error, ' + err
    }
});

const button = document.getElementById('increment-btn');
button.addEventListener('click', async _ => {
    const counter = document.getElementById('click-counter');
    try {
        counter.value = '#loading'
        const response = await fetch('/api/v1/click', {
          method: 'post'
        });
        if (response.ok) {
            const actualCount = await response.text();
            console.log(`increment() completed, actualCount=${actualCount}`);
            counter.value = actualCount;
        } else {
            const statusCode = response.status;
            console.log(`increment() failed, statusCode=${statusCode}`)
            counter.value = '#error, statusCode=' + statusCode;
        }
    } catch(err) {
        console.error('increment() failed', err);
        counter.value = '#error, ' + err
    }
});
