# Relevance Trilogy Demo

## Build and run this demo project

```bash
    $ mvn clean package && mvn -Pcargo.run
```

## Expressional Inference Rule Engine Demo

- See https://www.onehippo.org/library/enterprise/services-features/inference-engine/introduction.html
- Visit http://localhost:8080/cms/.
- Open and see "administration / Inference Rules / Determine Visitor Interest Type" document, which defines
  inference rules to deduce the goal variable, ```interestType```, from input variables such as request URI, ```Referer``` HTTP header, etc.
- Open the "Audiences" perspective and see how the Characteristics can be constructed using this inference rules.
- Open the "Channel Manager" perspective and see how the Essentials List component in the homepage can be configured
  with different targeting variants.
- Open http://localhost:8080/site/ and see how the homepage shows different data for each variant,
  based on the collected targeting data defined and evaluated from the inference rules.
- You can also set the "Custom Rules FQN" field in an inference rules document to use either
  a Spring bean component or a custom Java class
  instead of using the "Rules Expressions" field in JEXL.
  e.g, "demoContentInterestTypeInferenceCommand" or "org.example.relevance.trilogy.demo.integration.DemoContentInterestTypeInferenceCommand".

## API Agent Channel Demo

- See https://www.onehippo.org/library/enterprise/services-features/api-agent-channel/introduction.html

### GREB API Demo

- See https://www.onehippo.org/library/enterprise/services-features/greb-api/introduction.html

