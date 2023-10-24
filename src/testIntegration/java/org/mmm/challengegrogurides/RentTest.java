package org.mmm.challengegrogurides;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"org.mmm.challengegrogurides.glue.rent"},
        features = {"src/testIntegration/resources/bdd/rent"})
public class RentTest {
}
