package org.mytest;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.SNIPPET_TYPE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("org.mytest")
@ConfigurationParameter(key = SNIPPET_TYPE_PROPERTY_NAME, value = "CAMELCASE")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/cucumber-reports.html, json:target/cucumber-reports/cucumber-reports.json, junit:target/cucumber-reports/cucumber-reports.xml")
public class RunCucumberTest {
}