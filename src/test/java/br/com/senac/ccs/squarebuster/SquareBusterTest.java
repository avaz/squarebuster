package br.com.senac.ccs.squarebuster;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"br.com.senac.ccs.squarebuster", 
                         "cucumber.runtime.java.spring.hooks"})
public class SquareBusterTest {}