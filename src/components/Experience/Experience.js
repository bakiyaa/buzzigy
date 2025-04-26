import React from 'react';
import './Experience.css';

function Experience() {
  const experiences = [
    {
      role: "Software Engineer",
      company: "Capgemini",
      location: "Chennai, TN, INDIA",
      period: "08/2022 - Current",
      description: [
        "Developed scalable microservices and APIs for the Barclays LoanIQ using Spring Boot, Kafka, and Oracle, improving loan trading and processing efficiency.",
        "Built and deployed a microservice handling millions of requests, leveraging Spring Boot-SOAP and REST API, MongoDB, Kafka, Oath, and JWT for enhanced performance and security.",
        "Maintained and optimized the Barclays pool funding system in a real-time environment using Spring Boot, Camel, MQ, and Autosys, ensuring seamless integration and reliability. Resolved production issues and implemented fixes.",
        "Resolved critical issues in Market Pre-Trade Analysis, achieving a 43% efficiency boost by optimizing code and deploying fixes using Java 11, Hibernate, Drools, and SQL.",
        "Utilized CI/CD pipeline integration, pull requests, code reviews, unit/integration/e2e testing (Spring-Mockito, Git, Jira, Bitbucket, TeamCity, Sonar, Veracode) maintaining 20+ story points per sprint.",
        "Award: ‘Star of the Team’ for contributions to the Barclays TCW Market Pre-Trade Analysis project."
      ]
    },
    {
      role: "Systems Engineer",
      company: "TCS",
      location: "Chennai, TN, INDIA",
      period: "06/2018 - 08/2022",
      description: [
        "Engineered and deployed business process automation for British Telecom's KCIM E2E service, generating XML, SMS, and email notifications using Spring Boot, FreeMarker templates, Drools, and Oracle.",
        "Implemented a new package for updating British Telecom data flows by connecting Spring Boot to middleware, parsing XML, applying Drools rules, sending JMS messages/mail, and updating dashboard via REST API.",
        "Worked in Agile projects, participating in Scrum ceremonies, tracking defects in JIRA, and managing code through Git, Jenkins, and Sonar.",
        "Automated telecommunication journeys for Hutch UK using Python-based Selenium frameworks, generating reports and integrating with Grafana for real-time monitoring.",
        "Developed Unix/Linux shell scripts with regular expressions for dynamic data file processing.",
        "Written modular functions for Infor Manila automation using Selenium Java, TestNG, Apache POI, and Robot classes.",
        "Trained in Java, Spring MVC, REST API, Microservices, Hibernate, Selenium, UFT, JMeter, and MSSQL.",
        "Awards: ‘On Spot Award’ (British Telecom project), ‘Service & Commitment Award’ (Framework design, contributing to ‘Best Team Award’)."
      ]
    }
  ];

  return (
    <section id="experience" className="experience-section content-section">
      <h2>Work Experience</h2>
      {experiences.map((exp, index) => (
        <div key={index} className="experience-item">
          <h3>{exp.role} - {exp.company}</h3>
          <p className="experience-period">{exp.location} | {exp.period}</p>
          <ul>
            {exp.description.map((point, i) => (
              <li key={i}>{point}</li>
            ))}
          </ul>
        </div>
      ))}
    </section>
  );
}

export default Experience;
