/* 
 * Copyright 2016 King's College London, Richard Jackson <richgjackson@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.kcl.testservices;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import uk.ac.kcl.scheduling.SingleJobLauncher;
import uk.ac.kcl.utils.DbmsTestUtils;
import uk.ac.kcl.utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author rich
 */
@Service
@ComponentScan("uk.ac.kcl.it")
@Ignore
public class BiolarkWebserviceWithoutSchedulingTests {

    final static Logger logger = Logger.getLogger(BiolarkWebserviceWithoutSchedulingTests.class);

    @Autowired
    SingleJobLauncher jobLauncher;
    @Autowired
    private TestUtils testUtils;
    @Autowired
    DbmsTestUtils dbmsTestUtils;

//    Currently Biolark is unavailable for download
    @Ignore
    @Test
    @DirtiesContext
    public void biolarkTest() {
        jobLauncher.launchJob();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(75,testUtils.countOutputDocsInES());
        assertEquals(75,dbmsTestUtils.countRowsInOutputTable());

        assertTrue(testUtils.getStringInEsDoc("1")
                .contains("HP:0003510"));
    }



}
