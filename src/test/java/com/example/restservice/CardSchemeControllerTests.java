/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import static org.mockito.ArgumentMatchers.isNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CardSchemeControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void noParamShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/card-scheme/verify/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Card No: not provided"));

	}

	@Test
	public void pathVariableShouldReturnMessage() throws Exception {

		this.mockMvc.perform(get("/card-scheme/verify/{card}", "45717360"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{'success':true,'payload':{'scheme':'visa','type':'debit','bank':'Jyske Bank'}}"));
    }
    
    @Test
	public void statusShouldReturnMessage() throws Exception {
		this.mockMvc.perform(get("/card-scheme/stats").param("start", "1").param("limit", "3"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("{'start':1,'limit':3,'size':1,'payload':{'45717360':1}}"));
	}

}
